package sam.rus.App;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class App {
    public static void main(String[] args) {
        //SocketServer.startServer();
        String loginAndPassword = "samy:1111";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(loginAndPassword.getBytes(StandardCharsets.UTF_8));
        String s = new String(encode);
        System.out.println(s);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encode);
        String s1 = new String(decode);
        System.out.println(s1);

    }
}

