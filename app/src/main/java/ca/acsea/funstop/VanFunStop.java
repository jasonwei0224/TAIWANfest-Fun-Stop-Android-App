package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VanFunStop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_fun_stop);
    }

    public void startVanFunStopMapActivity(View view){
        Intent intent = new Intent(this, vanFunStopMap.class);
        startActivity(intent);
    }

    public void startQRCodeScannerActivity(View view){
        Intent intent = new Intent(this, vanCamera.class);
        startActivity(intent);
    }
    public void startVanNavMenuActivity(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }

}
