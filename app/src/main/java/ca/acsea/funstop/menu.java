package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class menu extends AppCompatActivity {
    //ImageButton torontobtn = findViewById(R.id.im2);
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void startVanNavMenu(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }
    public void startTorNavMenu(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }
}
