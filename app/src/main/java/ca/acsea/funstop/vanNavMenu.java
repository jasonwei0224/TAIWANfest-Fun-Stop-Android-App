package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class vanNavMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_nav_menu);
    }

    public void startAboutActivity(View view){
        Intent intent = new Intent(this, aboutacsea.class);
        startActivity(intent);
    }
    public void startVanFunstopActivity(View view){
        Intent intent = new Intent(this, rule.class);
        startActivity(intent);
    }

    public void startVanVenueMapActivity(View view){
        Intent intent = new Intent(this, map.class);
        startActivity(intent);
    }

    public void startVanSponsorActivity(View view){
        Intent intent = new Intent(this, sponsor.class);
        startActivity(intent);
    }

    public void strartVanScheduleActivity(View view){
        Intent intent = new Intent(this, schedule.class);
        startActivity(intent);
    }

    public void startPirvacyPolicyActivity(View view){
        Intent intent = new Intent(this, privacypolicy.class);
        startActivity(intent);
    }

    public void startTermOfUse(View view){
        Intent intent = new Intent(this, termofuse.class);
        startActivity(intent);
    }
}
