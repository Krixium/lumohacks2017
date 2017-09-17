package ca.bcit.cst.seta2016.invoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TopicsMenu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_menu);
    }

    public void toSex(View view)
    {
        Intent intent = new Intent(this, SexInfo.class);
        startActivity(intent);
    }

    public void toDrugs(View view)
    {
        Intent intent = new Intent(this, DrugInfo.class);
        startActivity(intent);
    }

    public void toBully(View view)
    {
        Intent intent = new Intent(this, BullyInfo.class);
        startActivity(intent);
    }

    public void toRel(View view)
    {
        Intent intent = new Intent(this, RelInfo.class);
        startActivity(intent);
    }
}
