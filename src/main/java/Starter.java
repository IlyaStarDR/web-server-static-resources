import server.Server;

import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3001);
        server.setWebappPath("src/main/resources/webapp");
        server.start();
    }
}
