package request;

import resource.ResourceReader;
import response.ResponseWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RequestHandler {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final String webappPath;

    public RequestHandler(BufferedReader bufferedReader, BufferedWriter bufferedWriter, String webappPath) {
        this.bufferedReader = bufferedReader;
        this.bufferedWriter = bufferedWriter;
        this.webappPath = webappPath;
    }

    public void handle() throws IOException {
        Request request = RequestParser.parse(bufferedReader);
        try {
            String content = ResourceReader.readResource(webappPath, request.getUri());
            ResponseWriter.writeSuccessResponse(content, bufferedWriter);
        } catch (FileNotFoundException e) {
            ResponseWriter.writeNotFoundResponse(bufferedWriter);
        }
    }
}
