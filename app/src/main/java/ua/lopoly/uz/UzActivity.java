package ua.lopoly.uz;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class UzActivity extends Activity  {

    private static final String DEBUG_TAG = "HttpExample";
    List<String> responseList;
    ArrayAdapter<String> adapter;
    AutoCompleteTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uz);
        responseList = new ArrayList<String>();


        final String url = "http://booking.uz.gov.ua/purchase/station/";

        textView  = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, responseList);

        textView.setAdapter(adapter);

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.length()>0 && responseList.isEmpty()){

                    try {
                        new FetchStationTask().execute(url + URLEncoder.encode(s.toString(), "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if(s.length()==0){
                    responseList.clear();
                    adapter.clear();
                }

            }
        });
    }

    private class FetchStationTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            try {
                return new UzFetcher().getUrlString(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result){

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                StationResponse st = objectMapper.readValue(result, StationResponse.class);

                for (int i = 0; i<st.mStations.size(); i++){
                        responseList.add(st.mStations.get(i).getTitle());
                }

                Log.i(DEBUG_TAG, responseList.get(0));

            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String x : responseList){
                adapter.add(x);
            }
            adapter.notifyDataSetChanged();
            Log.i(DEBUG_TAG, result);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uz, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


