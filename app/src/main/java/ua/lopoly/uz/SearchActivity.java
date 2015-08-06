package ua.lopoly.uz;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ua.lopoly.uz.TrainDataResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lopoly on 03.08.2015.
 */
public class SearchActivity extends Activity {
    TrainDataResponse st = new TrainDataResponse();

    final static String DEBUG_TAG = "SearchActivity";
    private TextView mNumTextView;
    private TextView mFromTillTextView;
    private TextView mDateTextView;
    private TextView mDepArrTextView;
    private TextView mDurationTextView;
    private TextView mFreePlacesTextView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        new FetchTrainTask().execute();



        mNumTextView = (TextView)findViewById(R.id.train_number);
        mFromTillTextView = (TextView)findViewById(R.id.from_till);
        mDateTextView = (TextView)findViewById(R.id.date);
        mDepArrTextView = (TextView)findViewById(R.id.dep_arr);
        mDurationTextView = (TextView)findViewById(R.id.duration);
        mFreePlacesTextView = (TextView)findViewById(R.id.free_places);



    }

    private class FetchTrainTask extends AsyncTask<String, Void, String> {
        private String mTrainNumber;
        private String mFrom;
        private String mTill;
        private Date mDateFrom;
        private Date mDateTill;
        private String mDeparture;
        private String mArrival;
        private Date mDuration;
        private String mLuxLet;
        private String mCupeLet;
        private Integer mLuxPlaces;
        private Integer mCupePlaces;

        @Override
        protected String doInBackground(String... urls) {
            try {
                return new TrainResponse().getUrlString();
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result){


            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                st = objectMapper.readValue(result, TrainDataResponse.class);

                /*for (int i = 0; i<st.mStations.size(); i++){
                    mResponseList.put(st.mStations.get(i).getTitle(),st.mStations.get(i).getStationId());
                }*/

                //Log.i(DEBUG_TAG, mResponseList.keySet().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            /*for (String x : mResponseList.keySet()){
                adapter.add(x);
            }
            adapter.notifyDataSetChanged();*/
            mTrainNumber = st.value.get(0).getNum();
            mFrom = st.value.get(0).getFrom().getStation();
            mTill = st.value.get(0).getTill().getStation();
            mDateFrom =new Date((long)st.value.get(0).getFrom().getDate()*1000);
            mDateTill =new Date((long)st.value.get(0).getTill().getDate()*1000);
            mDeparture = st.value.get(0).getFrom().getSrcDate();
            mArrival = st.value.get(0).getTill().getSrcDate();
            mDuration = new Date(mDateTill.getTime()- mDateFrom.getTime());
            mLuxLet = st.value.get(0).getTypes().get(0).getLetter();
            mCupeLet = st.value.get(1).getTypes().get(1).getLetter();
            mLuxPlaces = st.value.get(0).getTypes().get(0).getPlaces();
            mCupePlaces = st.value.get(1).getTypes().get(1).getPlaces();


            mNumTextView.setText(mTrainNumber);
            mFromTillTextView.setText(mFrom + "/" + mTill);
            mDateTextView.setText(dateFormat.format(mDateFrom) + "/" + dateFormat.format(mDateTill));
            mDepArrTextView.setText(mDeparture + "/" + mArrival);
            mDurationTextView.setText(hourFormat.format(mDuration));
            mFreePlacesTextView.setText(mLuxLet + " " +mLuxPlaces + "; "+mCupeLet + " "+ mCupePlaces);
            Log.i(DEBUG_TAG, st.toString());
        }
    }
}
