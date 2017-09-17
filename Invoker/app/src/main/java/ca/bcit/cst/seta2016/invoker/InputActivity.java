package ca.bcit.cst.seta2016.invoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    private EditText textFieldTitle;
    private EditText textFieldDesc;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        textFieldTitle = (EditText) findViewById(R.id.inputFieldTitle);
        textFieldDesc = (EditText) findViewById(R.id.inputFieldDesc);

        buttonAdd = (Button) findViewById(R.id.buttonSubmit);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("title", textFieldTitle.getText().toString());
                intent.putExtra("desc", textFieldDesc.getText().toString());
                startActivity(intent);
            }
        });
    }
}
