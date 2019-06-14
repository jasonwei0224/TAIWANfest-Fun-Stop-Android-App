package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torontoSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_schedule);
    }

    public void startTorNavMenue(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }
}
