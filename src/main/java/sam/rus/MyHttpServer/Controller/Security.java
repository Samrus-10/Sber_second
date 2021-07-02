package sam.rus.MyHttpServer.Controller;

import com.sun.net.httpserver.BasicAuthenticator;

public class Security extends BasicAuthenticator {
    public Security(String s) {
        super(s);
    }

    @Override
    public boolean checkCredentials(String login, String password) {
        System.out.printf("Login: %s, Password: %s\n",login, password);
        return (login.equals("samy")  && password.equals("1111"));
    }
}
