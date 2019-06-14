package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TorFunStop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fun_stop);
    }
    public void startTorFunStopMapActivity(View view){
        Intent intent = new Intent(this, torontoFunStopMap.class);
        startActivity(intent);
    }

    public void startQRCodeScannerActivity(View view){
        Intent intent = new Intent(this, torCamera.class);
        startActivity(intent);
    }

    public void startTorNavMenueActivity(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }
}
