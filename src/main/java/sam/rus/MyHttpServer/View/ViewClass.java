package sam.rus.MyHttpServer.View;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import sam.rus.MyHttpServer.Controller.HttpJsonHandler;
import sam.rus.MyHttpServer.Controller.MyHttpTestHandler;
import sam.rus.MyHttpServer.Controller.Security;
import sam.rus.MyHttpServer.Util.AccessRule;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ViewClass {
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 1025), 0);
            server.createContext("/test", new MyHttpTestHandler());
            HttpContext context = server.createContext("/cards", new HttpJsonHandler());
            context.setAuthenticator(new Security("cards", new AccessRule()));
            server.setExecutor(threadPoolExecutor);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
