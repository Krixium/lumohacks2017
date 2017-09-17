package ca.bcit.cst.seta2016.invoker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText to = (EditText) findViewById(R.id.sentTo);
        final EditText subject = (EditText) findViewById(R.id.subject);
        final EditText message = (EditText) findViewById(R.id.emailText);

        Button sentEmail = (Button)findViewById(R.id.submitButton);
        sentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toS = to.getText().toString();
                String subjectS = subject.getText().toString();
                String messageS = message.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, toS);
                email.putExtra(Intent.EXTRA_SUBJECT, subjectS);
                email.putExtra(Intent.EXTRA_TEXT, messageS);

                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an app to sent this"));
            }
        });
    }
}
