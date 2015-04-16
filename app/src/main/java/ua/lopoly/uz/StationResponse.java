package ua.lopoly.uz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by lopoly on 11.04.2015.
 */
public class StationResponse {

        ArrayList<Station> mStations;

        @JsonProperty("value")
        public ArrayList<Station> getStations() {
            return mStations;
        }

        @JsonProperty("value")
        public void setStations(ArrayList<Station> stations) {
            mStations = stations;
        }

        @Override
        public String toString() {
            return "StationResponse{" +
                    "mStations=" + mStations +
                    '}';
        }
    }

    class Station {
        String mTitle;
        long mStationId;

        @Override
        public String toString() {
            return "Station{" +
                    "mTitle='" + mTitle + '\'' +
                    ", mStationId=" + mStationId +
                    '}';
        }

        @JsonProperty("title")
        public String getTitle() {
            return mTitle;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            mTitle = title;
        }

        @JsonProperty("station_id")
        public long getStationId() {
            return mStationId;
        }

        @JsonProperty("station_id")
        public void setStationId(long stationId) {
            mStationId = stationId;
        }

    }

