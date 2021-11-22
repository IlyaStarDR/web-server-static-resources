package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static Request parse(BufferedReader bufferedReader) throws IOException {
        Request request = new Request();
        injectUriAndMethod(bufferedReader, request);
        injectHeaders(bufferedReader, request);
        return request;
    }

    private static void injectUriAndMethod(BufferedReader bufferedReader, Request request) throws IOException {
        String uriAndMethodAndVersion = bufferedReader.readLine();
        String[] startLine = uriAndMethodAndVersion.split("\s");
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
