package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class schedule extends AppCompatActivity {
    private ScrollView dayOne;
    private ScrollView dayTwo;
    private ScrollView dayThree;
    private ImageButton dayOneBtn;
    private ImageButton dayTwoBtn;
    private ImageButton dayThreeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        dayOne = findViewById(R.id.vanDay1);
        dayTwo = findViewById(R.id.vanDay2);
        dayThree = findViewById(R.id.vanDay3);
        dayOneBtn = findViewById(R.id.vanDayOneBtn);
        dayTwoBtn = findViewById(R.id.VanDayTwoBtn);
        dayThreeBtn = findViewById(R.id.VanDayThreeBtn);

        //dayOneBtn.setAlpha(1f);
        dayTwoBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(0.3f);
        dayOne.setVisibility(View.VISIBLE);
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.GONE);

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
    public void startVanVenueMap(View view){
        Intent intent =  new Intent(this, map.class);
        startActivity(intent);
    }
    public void startVanNavMenu(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, vanNavMenu.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }

    public void showScheduleOnFirstDay(){
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.GONE);
        dayOne.setVisibility(View.VISIBLE);

        dayOneBtn.setAlpha(1f);
        dayTwoBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(0.3f);
    }
    public void showScheduleOnSecondDay(){
        dayTwo.setVisibility(View.VISIBLE);
        dayThree.setVisibility(View.GONE);
        dayOne.setVisibility(View.GONE);

        dayOneBtn.setAlpha(0.3f);
        dayTwoBtn.setAlpha(1.0f);
        dayThreeBtn.setAlpha(0.3f);
    }
    public void showScheduleOnThirdDay(){
        dayTwo.setVisibility(View.GONE);
        dayThree.setVisibility(View.VISIBLE);
        dayOne.setVisibility(View.GONE);

        dayOneBtn.setAlpha(0.3f);
        dayTwoBtn.setAlpha(0.3f);
        dayThreeBtn.setAlpha(1f);
    }

}
