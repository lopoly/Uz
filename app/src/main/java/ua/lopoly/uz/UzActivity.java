package ua.lopoly.uz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;


public class UzActivity extends Activity  {

    private static final String DEBUG_TAG = "HttpExample";
    private Map<String, String> mResponseList;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView fromTextView;
    private AutoCompleteTextView tillTextView;
    private Button dateButton;
    private Button mSearchButton;
    private Spinner time;
    private String dateCalendar;
    static FormData mFormData;
    private ProgressBar mProgressBar;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        dateCalendar = data.getStringExtra(CalendarActivity.EXTRA_DATE);
        dateButton.setText(dateCalendar);
        mFormData.setDateDep(dateCalendar);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uz);

        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);

        mFormData = new FormData("s","s","s","s","s","s");

        mResponseList = new HashMap<String,String>();

        final String url = "http://booking.uz.gov.ua/purchase/station/";

        time = (Spinner)findViewById(R.id.time_spinner);
        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource
                (this, R.array.hours, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(spinnerAdapter);

        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mFormData.setTimeDep(time.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fromTextView  = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);
        tillTextView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView2);

        dateButton = (Button)findViewById(R.id.btnDate);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UzActivity.this, CalendarActivity.class);
                startActivityForResult(i,0);
            }

        });



        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                mResponseList.keySet().toArray(new String[mResponseList.size()]));

        fromTextView.setAdapter(adapter);
        tillTextView.setAdapter(adapter);

        fromTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!fromTextView.hasFocus()) {
                    mResponseList.clear();
                }
                Log.d(DEBUG_TAG, "work");
            }
        });


        fromTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0 && mResponseList.isEmpty()) {

                    try {
                        new FetchStationTask().execute(url + URLEncoder.encode(s.toString(), "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                if (s.length() == 0) {
                    mResponseList.clear();
                    adapter.clear();
                }
            }
        });
        fromTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = adapter.getItem(position);
                mFormData.setFrom(key);
                mFormData.setIdFrom(mResponseList.get(key));

            }
        });

        tillTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.length() > 0 && mResponseList.isEmpty()) {

                    try {
                        new FetchStationTask().execute(url + URLEncoder.encode(s.toString(), "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                if (s.length() == 0) {
                    mResponseList.clear();
                    adapter.clear();
                }
            }
        });
        tillTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = adapter.getItem(position);
                mFormData.setTill(key);
                mFormData.setIdTill(mResponseList.get(key));

            }
        });

        mSearchButton = (Button)findViewById(R.id.btnSearch);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UzActivity.this, SearchActivity.class);
                startActivity(i);
                Log.i(DEBUG_TAG, mFormData.toString());
            }
        });



    }

    private class FetchStationTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

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
                        mResponseList.put(st.mStations.get(i).getTitle(),st.mStations.get(i).getStationId());
                }

                //Log.i(DEBUG_TAG, mResponseList.keySet().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String x : mResponseList.keySet()){
                adapter.add(x);
            }
            adapter.notifyDataSetChanged();
            Log.i(DEBUG_TAG, result);
            mProgressBar.setVisibility(View.INVISIBLE);
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


