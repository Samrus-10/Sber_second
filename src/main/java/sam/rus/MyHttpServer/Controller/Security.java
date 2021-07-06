package sam.rus.MyHttpServer.Controller;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import sam.rus.MyHttpServer.Util.TokenUtil;

import java.util.Base64;

public class Security extends BasicAuthenticator {
    private boolean token;

    public Security(String s) {
        super(s);
    }

    @Override
    public Result authenticate(HttpExchange httpExchange) {
        System.out.println("authenticate...");
        Headers requestHeaders = httpExchange.getRequestHeaders();
        String authorization = requestHeaders.getFirst("Authorization");
        if (authorization == null) {
            String accessToken = requestHeaders.getFirst("Mytoken");
            if (accessToken != null) {
                if (this.checkCredentials(accessToken)) {
                    return new Success(new HttpPrincipal("token", this.realm));
                }
            }
            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
            return new Retry(401);
        } else {
            int var4 = authorization.indexOf(32);
            if (var4 != -1 && authorization.substring(0, var4).equals("Basic")) {
                byte[] var5 = Base64.getDecoder().decode(authorization.substring(var4 + 1));
                String var6 = new String(var5);
                int var7 = var6.indexOf(58);
                String login = var6.substring(0, var7);
                String password = var6.substring(var7 + 1);
                if (this.checkCredentials(login, password)) {
                    return new Success(new HttpPrincipal(login, this.realm));
                } else {
                    Headers var10 = httpExchange.getResponseHeaders();
                    var10.set("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
                    return new Failure(401);
                }
            } else {
                return new Failure(401);
            }
        }
    }

    @Override
    public boolean checkCredentials(String login, String password) {
        System.out.printf("Login: %s, Password: %s\n", login, password);
        return (login.equals("samy") && password.equals("1111"));
        //return token;
    }

    public boolean checkCredentials(String token) {
        System.out.println("here");
        return TokenUtil.cheackAccessToken(token);
    }
}
