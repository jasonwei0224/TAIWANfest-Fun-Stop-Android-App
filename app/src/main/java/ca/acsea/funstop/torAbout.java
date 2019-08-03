package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_about);
    }
    public void startTorNavMenuActivity(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, torontoNavMenue.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
}
