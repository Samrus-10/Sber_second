package sam.rus.App;


import sam.rus.MyHttpServer.Util.Base64Coder;
import sam.rus.MyHttpServer.Util.ToJSON;
import sam.rus.MyHttpServer.Util.TokenUtil;
import sam.rus.MyHttpServer.View.ViewClass;
import sam.rus.MyHttpServer.model.TokenDTO;
import sam.rus.MyHttpServer.model.User;

public class App {
    public static void main(String[] args) {
//        String sam = TokenUtil.creatToken("sam", "1111");
//        System.out.println(sam);
//        String[] split = sam.split("---");

//        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[i]);
//        }

//        String accessToken = split[0];
//        boolean b = TokenUtil.cheackAccessToken(accessToken);
//        System.out.println(b);
//        boolean b = TokenUtil.cheackRefreshToken("1", "ZjJkZGQ5MzRkZjU3ZDcwZWVlMWIxOGQ5MWY1YThiZDBlYmNhYWJmNjIyYzExNTEyZjk0MTE2NDMwYzdlOTdhMQ==c2Ft");
//        System.out.println(b);

        //TokenUtil.checkTimeOut("30", "21/07/05 12:37:00");

        //String hello_world = SHA256Coder.toCoderSHA256("Hello World", "key1");
        //System.out.println(hello_world);
//        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss"));
//        System.out.println(fileName);
//        ToJSON<User> userToJSON = new ToJSON<>();
//        String s = userToJSON.convertToJson(new User(1, "sam", "1111", "visitoer", null, null));
//        System.out.println(s);
//        User user = (User)userToJSON.convertToObject(s,new User());
//        System.out.println(user);
//
//        ToJSON<TokenDTO> tokenDTOToJSON = new ToJSON<>();
//        String s1 = tokenDTOToJSON.convertToJson(new TokenDTO(1, "now", "30", "admin"));
//        System.out.println(s);
//        TokenDTO token = (TokenDTO)userToJSON.convertToObject(s1,new TokenDTO());
//        System.out.println(token);

        ViewClass.startServer();
        //System.out.println(Base64Coder.toEncoder("samy:1111"));
    }
}
