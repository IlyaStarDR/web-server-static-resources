package server;

import request.RequestHandler;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String webappPath;

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);
        ) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                    RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, webappPath);
                    requestHandler.handle();
                }
            }
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebappPath(String webappPath) {
        this.webappPath = webappPath;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3001);
        server.setWebappPath("src/main/resources/webapp");
        server.start();
    }
}
