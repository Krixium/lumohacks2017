package ca.bcit.cst.seta2016.invoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    private EditText eventChild;
    private EditText eventDate;
    private EditText eventEvent;
    private EditText eventDesc;
    private EditText eventRank;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        eventChild = (EditText) findViewById(R.id.inputFieldChild);
        eventDate = (EditText) findViewById(R.id.inputFieldDate);
        eventEvent = (EditText) findViewById(R.id.inputFieldEvent);
        eventDesc = (EditText) findViewById(R.id.inputFieldDesc);
        eventRank = (EditText) findViewById(R.id.inputFieldRank);

        buttonAdd = (Button) findViewById(R.id.buttonSubmit);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("child", eventChild.getText().toString());
                intent.putExtra("date", eventDate.getText().toString());
                intent.putExtra("event", eventEvent.getText().toString());
                intent.putExtra("desc", eventDesc.getText().toString());
                intent.putExtra("rank", eventRank.getText().toString());
                startActivity(intent);
            }
        });
    }
}
