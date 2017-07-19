package models.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Lenovo on 19.07.2017.
 */
public class HttpService {
    public HttpService() {

    }

    public String connectAndResponse(String url) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        while( (line = reader.readLine()) != null){
               stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
