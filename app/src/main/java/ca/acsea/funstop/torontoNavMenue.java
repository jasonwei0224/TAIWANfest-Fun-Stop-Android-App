package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torontoNavMenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_nav_menue);
    }

    public void startTorAboutActivity(View view){
        Intent intent = new Intent(this, torAbout.class);
        startActivity(intent);
    }

    public void startTorFunstopActivity(View view){
        Intent intent = new Intent(this, torontoRule.class);
        startActivity(intent);
    }

    public void startTorVenueMapActivity(View view){
        Intent intent = new Intent(this, torontoVenueMap.class);
        startActivity(intent);
    }

    public void startTorSponsorActivity(View view){
        Intent intent = new Intent(this, torontoSponsor.class);
        startActivity(intent);
    }

    public void strartTorScheduleActivity(View view){
        Intent intent = new Intent(this, torontoSchedule.class);
        startActivity(intent);
    }

    public void startTorPirvacyPolicyActivity(View view){
        Intent intent = new Intent(this, torPrivacyPolicy.class);
        startActivity(intent);
    }

    public void startTorTermOfUse(View view){
        Intent intent = new Intent(this, torTermOfUse.class);
        startActivity(intent);
    }
}
