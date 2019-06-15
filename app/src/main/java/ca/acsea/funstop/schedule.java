package ca.acsea.funstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class schedule extends AppCompatActivity {
    private ImageButton dayOne;
    private ImageButton dayTwo;
    private ImageButton dayThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        dayOne = findViewById(R.id.vanDayOneBtn);
        dayTwo = findViewById(R.id.VanDayTwoBtn);
        dayThree = findViewById(R.id.VanDayThreeBtn);
        dayTwo.setAlpha(0.3f);
        dayThree.setAlpha(0.3f);

        dayOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnFirstDay();
            }
        });
        dayTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnSecondDay();
            }
        });
        dayThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnThirdDay();
            }
        });
    }

    public void startVanNavMenu(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }


    public void showScheduleOnFirstDay(){
        dayTwo.setAlpha(0.3f);
        dayThree.setAlpha(0.3f);
        dayOne.setAlpha(1.0f);
    }
    public void showScheduleOnSecondDay(){
        dayOne.setAlpha(0.3f);
        dayThree.setAlpha(0.3f);
        dayTwo.setAlpha(1.0f);
    }
    public void showScheduleOnThirdDay(){
        dayOne.setAlpha(0.3f);
        dayTwo.setAlpha(0.3f);
        dayThree.setAlpha(1.0f);
    }
}
