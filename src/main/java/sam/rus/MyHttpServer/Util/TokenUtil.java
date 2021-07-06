package sam.rus.MyHttpServer.Util;

import sam.rus.MyHttpServer.DAO.PersonDAO;
import sam.rus.MyHttpServer.model.TokenDTO;
import sam.rus.MyHttpServer.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class TokenUtil {
    private static final String SECRET_KEY = "keys184wowha";

    public static String creatToken(String login, String password) {
        String header = "{\"alg\":\"HS256\", \"typ\":\"JWT\"}";
        User user = PersonDAO.getUser(login, password);
        String nowData = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"));
        String payload = String.format(
                "{\"userId\":\"%d\", \"data\":\"%s\",\"exp\":\"30\", \"rule\":\"%s\", \"login\":\"%s\"}",
                user.getId(), nowData, user.getRule(), user.getLogin());
        String unsignedToken = Base64Coder.toEncoder(header) + "." + Base64Coder.toEncoder(payload);
        String signature = SHA256Coder.toCoderSHA256(unsignedToken, SECRET_KEY);
        String accessToken = unsignedToken + "." + Base64Coder.toEncoder(signature);
        String refreshToken = Base64Coder.toEncoder(signature) + Base64Coder.toEncoder(login);
        PersonDAO.setToken(String.valueOf(user.getId()), refreshToken, accessToken);
        return String.format("%s---%s", accessToken, refreshToken);
    }

    //public static String updateToken(){}

    public static boolean cheackAccessToken(String token) {
        String[] split = token.split("\\.");
        Map<String, String> valueFromToken = parseTokken(split[1]);
        if (checkTokenInBD(valueFromToken.get("id_user"), token, "token_access")) {
            if (checkNotChange(split)) {
                return true;
            }
        }
        return false;
    }

    public static boolean cheackRefreshToken(String idUser, String token) {
        return checkTokenInBD(idUser, token, "token_refresh");
    }

    public static boolean checkTimeOut(String delta, String date) {
        boolean result = false;
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        String nowData = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"));
        Date old = new Date();
        Date current = new Date();
        try {
            old = format.parse(date);
            current = format.parse(nowData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = current.getTime() - old.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
//        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
//        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
//        System.out.println("Time in hours: " + diffHours + " hours.");
        if (diffMinutes < Integer.parseInt(delta) && diffHours == 0) {
            result = true;
        }
        return result;
    }

    public static Map<String, String> parseTokken(String payload) {
        String s = Base64Coder.toDecoder(payload);
        ToJSON<TokenDTO> tokenDTOToJSON = new ToJSON<>();
        TokenDTO jsonConvert = (TokenDTO) tokenDTOToJSON.convertToObject(s, new TokenDTO());
        HashMap<String, String> map = new HashMap<>();
        map.put("id_user", String.valueOf(jsonConvert.getUserId()));
        map.put("data", jsonConvert.getData());
        map.put("exp", jsonConvert.getExp());
        map.put("rule", jsonConvert.getRule());
        map.put("login", jsonConvert.getLogin());
        return map;
    }

    private static boolean checkNotChange(String[] split) {
        String newUnsignedToken = split[0] + "." + split[1];
        String newSignature = Base64Coder.toEncoder(SHA256Coder.toCoderSHA256(newUnsignedToken, SECRET_KEY));
        return newSignature.equals(split[2]);
    }

    private static boolean checkTokenInBD(String idUser, String token, String typeToken) {
        String refreshToken = PersonDAO.getToken(Integer.parseInt(idUser), typeToken);
        return refreshToken.equals(token);
    }

    private static boolean deleteOldToken(String idUser){
        return PersonDAO.deleteToken(Integer.parseInt(idUser));
    }
}
