package ua.lopoly.uz;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by lopoly on 11.04.2015.
 */
public class UzFetcher {
   public String getUrlString(String urlSpec) throws IOException{

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine = "";
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
             response.append(inputLine);
        }
        in.close();
        return response.toString();


    }
}
