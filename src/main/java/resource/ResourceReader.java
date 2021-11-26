package resource;

import exception.ServerException;

import java.io.*;

import static exception.StatusCode.NOT_FOUND;

public class ResourceReader {
    private final String webappPath;

    public ResourceReader(String webappPath) {
        this.webappPath = webappPath;
    }

    public InputStream readResource(String uri) {
        try {
            return new FileInputStream(new File(webappPath, uri));
        } catch (FileNotFoundException e) {
            throw new ServerException(NOT_FOUND);
        }
    }
}
