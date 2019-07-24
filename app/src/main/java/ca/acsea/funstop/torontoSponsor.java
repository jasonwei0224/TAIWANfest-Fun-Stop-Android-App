package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torontoSponsor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_sponsor);
    }

    public void startTorNavMenue(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }
}
