package sam.rus.MyHttpServer.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sam.rus.MyHttpServer.model.Card;
import sam.rus.MyHttpServer.model.ErrorRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpJsonHandler implements HttpHandler {
    private List<Card> listOfCards = new ArrayList<>(Arrays.asList(
            new Card(1, "1111 2222 3333 4444"),
            new Card(2, "1111 2222 3333 4444"),
            new Card(3, "1111 2222 3333 4444"),
            new Card(4, "1111 2222 3333 4444"),
            new Card(5, "1111 2222 3333 4444")
    ));

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("get".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            getHandler(httpExchange);
        } else if ("post".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            postHandler(httpExchange);
        }
    }

    private void getHandler(HttpExchange httpExchange) {
        System.out.println("getHandler...");
        StringWriter resultJsonString = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                OutputStream out = httpExchange.getResponseBody();
        ) {
            objectMapper.writeValue(resultJsonString, new ErrorRequest("This information don't use by this method"));
            httpExchange.sendResponseHeaders(200, resultJsonString.toString().length());
            out.write(resultJsonString.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postHandler(HttpExchange httpExchange) {
        System.out.println("postHandler...");
        StringWriter resultJsonString = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                OutputStream out = httpExchange.getResponseBody();
        ) {
            objectMapper.writeValue(resultJsonString, listOfCards);
            httpExchange.sendResponseHeaders(200, resultJsonString.toString().length());
            out.write(resultJsonString.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
