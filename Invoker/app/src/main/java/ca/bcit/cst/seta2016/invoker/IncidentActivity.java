package ca.bcit.cst.seta2016.invoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

public class IncidentActivity extends AppCompatActivity {

    private TextView displayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDate = (TextView) findViewById(R.id.date);
        Date date = get();
        System.out.print(date.toString());
        displayDate.setText(date.toString());
    }

    public Date get(){
        Bundle second = getIntent().getExtras();
        return new Date(second.getInt("Year"),
                second.getInt("Month"),second.getInt("Day"));
    }
}