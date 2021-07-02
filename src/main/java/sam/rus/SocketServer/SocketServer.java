package sam.rus.SocketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SocketServer extends Thread {
    private Socket socket;

    public static void startServer() {
        try (
                ServerSocket serverSocket = new ServerSocket(1025);
        ) {
            while (true) {
                new SocketServer(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketServer(Socket socket) {
        this.socket = socket;

        start();
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        ) {
//            List<String> requestList = new ArrayList<>();
//            String nextLine;
//            System.out.println("Before while");
//            while ((nextLine = in.readLine()) != null) {
//                System.out.printf("In while: %s\n", nextLine);
//                requestList.add(nextLine);
//            }
//            System.out.println("After while");
            String s = in.readLine();
            System.out.println(s);

            out.println("Works");
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
