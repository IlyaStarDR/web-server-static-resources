package response;

import exception.StatusCode;

import java.io.*;

public class ResponseWriter {

    public static void writeSuccessResponse(InputStream content, OutputStream outputStream) throws IOException {
        outputStream.write(("HTTP/1.1 " + StatusCode.OK.getCode() + " " + StatusCode.OK.getStatusText() + "\n\n").getBytes());
        byte[] buffer = new byte[2048];
        int count;
        while ((count = content.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
        }
    }

    public static void writeError(OutputStream outputStream, StatusCode statusCode) throws IOException {
        byte[] response = ("HTTP/1.1 " + statusCode.getCode() + " " + statusCode.getStatusText()).getBytes();
        outputStream.write(response);
    }
}
