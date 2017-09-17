package ca.bcit.cst.seta2016.invoker;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DrugTopic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_topic);

        getSupportActionBar().setSubtitle("Drugs");
    }
}
