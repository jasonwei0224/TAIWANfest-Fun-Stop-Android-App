package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class torontoRule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_rule);

    }
    public void onBackPressed() {
        Intent intent = new Intent(this, torontoNavMenue.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
    public void startTorFunStopActivity(View view){
        Intent intent = new Intent(this, TorFunStop.class);
        startActivity(intent);
    }
}
