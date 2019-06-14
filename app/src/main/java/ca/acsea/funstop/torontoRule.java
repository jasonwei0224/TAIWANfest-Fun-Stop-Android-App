package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torontoRule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_rule);
    }

    public void startTorFunStopActivity(View view){
        Intent intent = new Intent(this, TorFunStop.class);
        startActivity(intent);
    }
}
