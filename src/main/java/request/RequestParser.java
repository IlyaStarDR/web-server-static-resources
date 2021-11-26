package request;

import exception.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static exception.StatusCode.BAD_REQUEST;
import static exception.StatusCode.METHOD_NOT_ALLOWED;

public class RequestParser {

    public static Request parse(BufferedReader bufferedReader) throws IOException {
        Request request = new Request();
        injectUriAndMethod(bufferedReader, request);
        injectHeaders(bufferedReader, request);
        return request;
    }

    private static void injectUriAndMethod(BufferedReader bufferedReader, Request request) throws IOException {
        String uriAndMethodAndVersion = bufferedReader.readLine();
        if (uriAndMethodAndVersion == null) {
            throw new ServerException(BAD_REQUEST);
        }
        String[] startLine = uriAndMethodAndVersion.split("\s");
        if (!startLine[1].equals(HttpMethod.GET.toString())) {
            throw new ServerException(METHOD_NOT_ALLOWED);
        }
        request.setMethod(HttpMethod.valueOf(startLine[0].trim()));
        request.setUri(startLine[1].trim());
        request.setVersion(startLine[2].trim());
    }

    private static void injectHeaders(BufferedReader bufferedReader, Request request) throws IOException {
        Map<String, String> headersMap = new HashMap<>();
        while (true) {
            String headersLine = bufferedReader.readLine();
            String[] header = headersLine.split(": ");
            if (headersLine.equals("")) {
                break;
            }
            headersMap.put(header[0].trim(), header[1].trim());
        }
        request.setHeaders(headersMap);
    }
}
