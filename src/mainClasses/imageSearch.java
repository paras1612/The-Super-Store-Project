package mainClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class imageSearch {
    public String imgsearch(String product) throws IOException {
        System.setProperty("http.agent", "Chrome");
        String urlString = "https://www.google.co.in/search?hl=en&tbm=isch&source=hp&biw=1536&bih=674&ei=cQADXJzYNpTGvQTLlrmwAw&q=";
        URL url = new URL(urlString + product);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF8"));
        String line;
        String[] resp = new String[100];
        while ((line = reader.readLine()) != null) {
            resp = (line.split("\""));

          //  System.out.println(line);
        }
        for (int i = 0; i < resp.length; i++) {
            if (resp[i].contains("images?q")) {
                //System.out.println(resp[i]);
                if (i > 4) {
                    reader.close();
                    return resp[i];
                }
            }
        }
        reader.close();

        return null;
        // close our reader

    }
}


