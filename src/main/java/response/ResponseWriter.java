package response;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {

    public static void writeSuccessResponse(String content, BufferedWriter bufferedWriter) throws IOException {
        String response = "HTTP/1.1 200 OK\n\n" + content;
        bufferedWriter.write(response);
        bufferedWriter.flush();
    }

    public static void writeNotFoundResponse(BufferedWriter bufferedWriter) throws IOException {
        String response = "HTTP/1.1 404 Not Found\n\n";
        bufferedWriter.write(response);
        bufferedWriter.flush();
    }
}
