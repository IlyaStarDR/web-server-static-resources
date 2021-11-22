package resource;

import response.ResponseWriter;

import java.io.*;

public class ResourceReader {

    public static String readResource(String webappPath, String uri) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(webappPath, uri))))
        {
            String currentLine;
            StringBuilder content = new StringBuilder();
            while ((currentLine = bufferedReader.readLine()) != null)
            {
                content.append(currentLine);
                content.append("\n");
            }
            return content.toString();
        }
    }
}
