package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class torontoSchedule extends AppCompatActivity {

    private ScrollView torDayOne;
    private ScrollView torDayTwo;
    private ScrollView torDayThree;

    private ImageButton torDayOneBtn;
    private ImageButton torDayTwoBtn;
    private ImageButton torDayThreeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_schedule);

        torDayOneBtn = findViewById(R.id.dayOneBtn);
        torDayTwoBtn = findViewById(R.id.dayTwoBtn);
        torDayThreeBtn = findViewById(R.id.dayThreeBtn);

        torDayOne  = findViewById(R.id.torDay1);
        torDayTwo = findViewById(R.id.torDay2);
        torDayThree = findViewById(R.id.torDay3);
        torDayOne.setVisibility(View.VISIBLE);
        torDayTwo.setVisibility(View.GONE);
        torDayThree.setVisibility(View.GONE);

        torDayTwoBtn.setAlpha(0.3f);
        torDayThreeBtn.setAlpha(0.3f);
        //set on click listener  for all three btns
        torDayOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnFirstDay();
            }
        });
        torDayTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnSecondDay();
            }
        });
        torDayThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScheduleOnThirdDay();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, torontoNavMenue.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }

    public void startTorNavMenue(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);

    }
    public void startTorVenueMap(View view){
        Intent intent = new Intent(this, torontoVenueMap.class);
        startActivity(intent);
    }
    public void showScheduleOnFirstDay(){
        torDayTwoBtn.setAlpha(0.3f);
        torDayThreeBtn.setAlpha(0.3f);
        torDayOneBtn.setAlpha(1.0f);
        torDayTwo.setVisibility(View.GONE);
        torDayThree.setVisibility(View.GONE);
        torDayOne.setVisibility(View.VISIBLE);
    }
    public void showScheduleOnSecondDay(){
        torDayOneBtn.setAlpha(0.3f);
        torDayThreeBtn.setAlpha(0.3f);
        torDayTwoBtn.setAlpha(1.0f);
        torDayTwo.setVisibility(View.VISIBLE);
        torDayThree.setVisibility(View.GONE);
        torDayOne.setVisibility(View.GONE);
    }
    public void showScheduleOnThirdDay(){
        torDayOneBtn.setAlpha(0.3f);
        torDayTwoBtn.setAlpha(0.3f);
        torDayThreeBtn.setAlpha(1.0f);
        torDayTwo.setVisibility(View.GONE);
        torDayThree.setVisibility(View.VISIBLE);
        torDayOne.setVisibility(View.GONE);
    }
}
