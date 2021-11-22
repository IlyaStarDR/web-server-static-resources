package resource;

import java.io.*;

public class ResourceReader {
    private String webappPath;

    public String readResource(String uri) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(webappPath, uri)))) {
            String currentLine;
            StringBuilder content = new StringBuilder();
            while ((currentLine = bufferedReader.readLine()) != null) {
                content.append(currentLine);
                content.append("\n");
            }
            return content.toString();
        }
    }

    public void setWebappPath(String webappPath) {
        this.webappPath = webappPath;
    }
}
