package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void startVanMainMenuActivity(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }
}
