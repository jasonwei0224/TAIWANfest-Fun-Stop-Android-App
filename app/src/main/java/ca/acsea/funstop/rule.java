package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class rule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, vanNavMenu.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
    public void startVanFunStopActivity(View view){
        Intent intent = new Intent(this, VanFunStop.class);
        startActivity(intent);
    }
}
