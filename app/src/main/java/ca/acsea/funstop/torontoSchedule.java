package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class torontoSchedule extends AppCompatActivity {
    private ImageButton dayOne;
    private ImageButton dayTwo;
    private ImageButton dayThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_schedule);

        dayOne = findViewById(R.id.dayOneBtn);
        dayTwo = findViewById(R.id.dayTwoBtn);
        dayThree = findViewById(R.id.dayThreeBtn);
        dayTwo.setAlpha(0.3f);
        dayThree.setAlpha(0.3f);
        //set on click listener  for all three btns
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



    public void startTorNavMenue(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
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
