package ca.bcit.cst.seta2016.invoker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SexTopic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_topic);

        getSupportActionBar().setSubtitle(R.string.topicMenuSex);
    }
}
