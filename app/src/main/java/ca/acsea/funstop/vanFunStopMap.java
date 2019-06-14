package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class vanFunStopMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_fun_stop_map);
    }

    public void startVanFunStopActivity(View view){
        Intent intent = new Intent(this, VanFunStop.class);
        startActivity(intent);
    }
}
