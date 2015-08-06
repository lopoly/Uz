package ua.lopoly.uz;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lopoly on 03.08.2015.
 */
public class FormData {
    private String mIdFrom;
    private String mFrom;
    private String mIdTill;
    private String mTill;
    private String mDateDep;
    private String mTimeDep;

    public FormData(String idFrom, String from, String idTill, String till, String dateDep, String timeDep) {
        mIdFrom = idFrom;
        mFrom = from;
        mIdTill = idTill;
        mTill = till;
        mDateDep = dateDep;
        mTimeDep = timeDep;
    }

    public String getIdFrom() {
        return mIdFrom;
    }

    public void setIdFrom(String idFrom) {
        mIdFrom = idFrom;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;

    }

    public String getIdTill() {
        return mIdTill;
    }

    public void setIdTill(String idTill) {
        mIdTill = idTill;
    }

    public String getTill() {
        return mTill;
    }

    public void setTill(String till) {
        mTill = till;

    }

    public String getDateDep() {
        return mDateDep;
    }

    public void setDateDep(String dateDep) {
        mDateDep = dateDep;
    }

    public String getTimeDep() {
        return mTimeDep;
    }

    public void setTimeDep(String timeDep) {
        mTimeDep = timeDep;

    }

    @Override
    public String toString() {
        String s = "groovy";
        try{
        s = "station_id_from="+mIdFrom+"&station_id_till="+mIdTill+
                "&station_from="+URLEncoder.encode(mFrom, "UTF-8")+
                "&station_till="+URLEncoder.encode(mFrom, "UTF-8")+
                "&date_dep="+mDateDep+"&time_dep="+URLEncoder.encode(mTimeDep, "UTF-8")+
                "&time_dep_till=&another_ec=0&search=";
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return s;
    }
}
