package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class torontoSchedule extends AppCompatActivity {

    private ScrollView dayOne;
    private ScrollView dayTwo;
    private ScrollView dayThree;

    private ImageButton dayOneBtn;
    private ImageButton dayTwoBtn;
    private ImageButton dayThreeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_schedule);

        dayOneBtn = findViewById(R.id.dayOneBtn);
        dayTwoBtn = findViewById(R.id.dayTwoBtn);
        dayThreeBtn = findViewById(R.id.dayThreeBtn);

        dayOne  = findViewById(R.id.torDay1);
        dayTwo = findViewById(R.id.torDay2);
        dayThree = findViewById(R.id.torDay3);
        dayOne.setVisibility(View.VISIBLE);
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.GONE);

        dayTwoBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(0.3f);
        //set on click listener  for all three btns
        dayOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnFirstDay();
            }
        });
        dayTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnSecondDay();
            }
        });
        dayThreeBtn.setOnClickListener(new View.OnClickListener() {
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
        dayTwoBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(0.3f);
        dayOneBtn.setAlpha(1.0f);
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.GONE);
        dayOne.setVisibility(View.VISIBLE);
    }
    public void showScheduleOnSecondDay(){
        dayOneBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(0.3f);
        dayTwoBtn.setAlpha(1.0f);
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.GONE);
        dayOne.setVisibility(View.VISIBLE);
    }
    public void showScheduleOnThirdDay(){
        dayOneBtn.setAlpha(0.3f);
        dayTwoBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(1.0f);
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.GONE);
        dayOne.setVisibility(View.VISIBLE);
    }
}
