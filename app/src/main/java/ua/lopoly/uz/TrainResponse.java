package ua.lopoly.uz;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lopoly on 03.08.2015.
 */
public class TrainResponse {

    public String getUrlString() throws IOException {

        final String url = "http://booking.uz.gov.ua/purchase/search/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Referer", "http://booking.uz.gov.ua/");
        con.setRequestProperty("GV-Token", "c7212a338b31fc952f9601a0a7c4a533");
        con.setRequestProperty("GV-Ajax", "1");
        con.setRequestProperty("Cookie", "_gv_sessid=bnkc9f6ad60h79dho4gfm404r7; _gv_lang=uk; " +
                "HTTPSERVERID=server2; __utmt=1;" +
                " __utma=31515437.46774957.1429278713.1429291416.1429346067.4;" +
                " __utmb=31515437.1.10.1429346067; __utmc=31515437;" +
                " __utmz=31515437.1429278713.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");


        String urlParameters =UzActivity.mFormData.toString();


        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        Log.i("Debug", response.toString());
        return response.toString();

    }
}
