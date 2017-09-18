package ca.bcit.cst.seta2016.invoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;



public class CalendarActivity extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                Bundle date = new Bundle();
                date.putInt("Day", dayOfMonth);
                date.putInt("Month", month);
                date.putInt("Year", year);
                Intent in=new Intent(CalendarActivity.this,IncidentActivities.class);
                in.putExtras(date);
                startActivity(in);
            }
        });
    }
}