package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torontoFunStopMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_fun_stop_map);
    }
    public void startTorNavMenueActivity(View view){
        Intent intent = new Intent(this, TorFunStop.class);
        startActivity(intent);
    }
}
