package sam.rus.MyHttpServer.Controller;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import sam.rus.MyHttpServer.Util.AccessRule;
import sam.rus.MyHttpServer.Util.TokenUtil;

import java.util.Base64;
import java.util.Map;

public class Security extends BasicAuthenticator {
    private AccessRule accessRule;

    public Security(String s, AccessRule accessRule) {
        super(s);
        this.accessRule = accessRule;
    }

    @Override
    public Result authenticate(HttpExchange httpExchange) {
        System.out.println("authenticate...");
        Headers requestHeaders = httpExchange.getRequestHeaders();
        String authorization = requestHeaders.getFirst("Authorization");
        if (authorization == null) {
            String accessToken = requestHeaders.getFirst("MyToken");
            if (accessToken != null) {
                System.out.println("Token");
                if (TokenUtil.cheackAccessToken(accessToken)) {
                    System.out.println("checkToken");
                    String[] split = accessToken.split("\\.");
                    Map<String, String> stringStringMap = TokenUtil.parseTokken(split[1]);
                    String login = stringStringMap.get("login");
                    String rule = stringStringMap.get("rule");
                    String exp = stringStringMap.get("exp");
                    String data = stringStringMap.get("data");
                    if (TokenUtil.checkTimeOut(exp,data)) {
                        System.out.println("Time in");
                        if (this.checkCredentials(rule)) {
                            return new Success(new HttpPrincipal(login, this.realm));
                        } else {
                            Headers responseHeaders = httpExchange.getResponseHeaders();
                            responseHeaders.set("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
                            return new Retry(403);
                        }
                    } else {
                        System.out.println("Time out");
                        Headers responseHeaders = httpExchange.getResponseHeaders();
                        responseHeaders.set("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
                        return new Retry(401);
                    }
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
                return new Failure(400);
            }
        }
    }

    @Override
    public boolean checkCredentials(String login, String password) {
        System.out.printf("Login: %s, Password: %s\n", login, password);
        return this.accessRule.checkAccess(login, password, this.realm);
    }


    public boolean checkCredentials(String rule) {
        System.out.println(rule);
        return this.accessRule.checkAccess(rule, this.realm);
    }
}
