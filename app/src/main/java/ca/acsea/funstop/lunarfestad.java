package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class lunarfestad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunarfestad);
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, vanNavMenu.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
}
