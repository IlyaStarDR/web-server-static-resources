package server;

import request.RequestHandler;
import resource.ResourceReader;

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
                     OutputStream outputStream = socket.getOutputStream()) {
                    ResourceReader resourceReader = new ResourceReader(webappPath);
                    RequestHandler requestHandler = new RequestHandler(bufferedReader, outputStream, resourceReader);
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
}
