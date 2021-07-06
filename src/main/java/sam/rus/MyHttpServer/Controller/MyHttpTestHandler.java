package sam.rus.MyHttpServer.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyHttpTestHandler implements HttpHandler {
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

    private void handleResponse(HttpExchange exchange) {
        String htmlResponse = "<h1 align='center'>" +
                "works_1_for_test" +
                "</h1>";
        try (
                PrintWriter os = new PrintWriter(new BufferedWriter(new OutputStreamWriter(exchange.getResponseBody())), true);
                OutputStream out = exchange.getResponseBody();
        ) {
            exchange.sendResponseHeaders(200, htmlResponse.length());
            out.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        return "";
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return "";
    }
}


