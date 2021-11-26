package request;

import exception.ServerException;
import resource.ResourceReader;
import response.ResponseWriter;

import java.io.*;

public class RequestHandler {
    private final BufferedReader bufferedReader;
    private final OutputStream outputStream;
    private final ResourceReader resourceReader;

    public RequestHandler(BufferedReader bufferedReader, OutputStream outputStream, ResourceReader resourceReader) {
        this.bufferedReader = bufferedReader;
        this.outputStream = outputStream;
        this.resourceReader = resourceReader;
    }

    public void handle() throws IOException {
        Request request = RequestParser.parse(bufferedReader);
        try (InputStream content = resourceReader.readResource(request.getUri())) {
            ResponseWriter.writeSuccessResponse(content, outputStream);
        } catch (ServerException e) {
            ResponseWriter.writeError(outputStream, e.getStatusCode());
        }
    }
}
