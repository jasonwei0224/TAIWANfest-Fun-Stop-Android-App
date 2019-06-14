package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torPrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_privacy_policy);
    }
    public void startTorNavMenuActivity(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }
}
