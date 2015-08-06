package ua.lopoly.uz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;

/**
 * Created by lopoly on 31.07.2015.
 */
public class CalendarActivity extends Activity {

    private CalendarView mCalendar;

    public static final String EXTRA_DATE = "ua.lopoly.uz.date";

    private void setDateResult(String date){

        Intent data = new Intent();
        data.putExtra(EXTRA_DATE, date);
        setResult(RESULT_OK,data);

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendar = (CalendarView)findViewById(R.id.calendarView);
        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;


                String selectedDate = new StringBuilder().append(mDay)
                            .append(".").append(mMonth + 1).append(".").append(mYear)
                            .append(" ").toString();


                setDateResult(selectedDate);
            }
        });
    }
}
