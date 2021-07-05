package sam.rus.App;


import sam.rus.MyHttpServer.DAO.PersonDAO;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class App {
    public static void main(String[] args) {
        System.out.println(PersonDAO.enter("sam", "1111"));
    }
}

