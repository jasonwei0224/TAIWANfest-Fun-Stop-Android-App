package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class aboutacsea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutacsea);
    }

    public void startVanNavMenuActivity(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }
}
