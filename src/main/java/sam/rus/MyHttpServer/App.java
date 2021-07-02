package sam.rus.MyHttpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import sam.rus.MyHttpServer.View.ViewClass;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class App {
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    private static class MyHttpHandler implements HttpHandler {

        private void handleResponse(HttpExchange exchange) {
            String htmlResponse = "<h1 align='center'>" +
                    "works_1" +
                    "</h1>";
            try (
                    PrintWriter os = new PrintWriter(new BufferedWriter(new OutputStreamWriter(exchange.getResponseBody())), true);
                    OutputStream out = exchange.getResponseBody();
            ) {
                exchange.sendResponseHeaders(200, htmlResponse.length());
                //os.write(String.valueOf(htmlResponse.getBytes(StandardCharsets.UTF_8)));
                out.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void handle(com.sun.net.httpserver.HttpExchange httpExchange) throws IOException {
            String requestParamValue = null;
            if ("GET".equals(httpExchange.getRequestMethod())) {
                requestParamValue = handleGetRequest(httpExchange);
            } else if ("POST".equals(httpExchange)) {
                requestParamValue = handlePostRequest(httpExchange);
            }
            handleResponse(httpExchange);
        }

        private String handlePostRequest(HttpExchange httpExchange) {
            return "";
        }

        private String handleGetRequest(HttpExchange httpExchange) {
            return "";
        }
    }

    public static void main(String[] args) throws IOException {
//        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 1025), 0);
//        server.createContext("/test", new MyHttpHandler());
//        server.setExecutor(threadPoolExecutor);
//        server.start();
        ViewClass.startServer();
    }
}


