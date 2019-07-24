package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;

public class schedule extends AppCompatActivity {
    private ImageButton dayOne;
    private ImageButton dayTwo;
    private ImageButton dayThree;
    private TableLayout dayOneProgramOne;
    private TableLayout dayOneProgramTwo;
    private TableLayout dayOneProgramThree;
    private TableLayout dayOneProgramFour;
    private TableLayout dayOneProgramFive;
    private TableLayout dayOneProgramSix;
    private TableLayout dayOneProgramSeven;
    private TableLayout dayOneProgramEight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        dayOne = findViewById(R.id.vanDayOneBtn);
        dayTwo = findViewById(R.id.VanDayTwoBtn);
        dayThree = findViewById(R.id.VanDayThreeBtn);
        dayOneProgramOne = findViewById(R.id.dayOneProgramOne);
        dayOneProgramTwo = findViewById(R.id.dayOneProgramTwo);
        dayOneProgramThree = findViewById(R.id.dayOneProgramThree);
        dayOneProgramFour = findViewById(R.id.dayOneProgramFour);
        dayOneProgramFive = findViewById(R.id.dayOneProgramFive);
        dayOneProgramSix = findViewById(R.id.dayOneProgramSix);
        dayOneProgramSeven = findViewById(R.id.dayOneProgramSeven);

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
        dayOneProgramOne.setVisibility(View.VISIBLE);
        dayOneProgramTwo.setVisibility(View.VISIBLE);
        dayOneProgramThree.setVisibility(View.VISIBLE);
        dayOneProgramFour.setVisibility(View.VISIBLE);
        dayOneProgramFive.setVisibility(View.VISIBLE);
        dayOneProgramSix.setVisibility(View.VISIBLE);
        dayOneProgramSeven.setVisibility(View.VISIBLE);
        dayThree.setAlpha(0.3f);
        dayOne.setAlpha(1.0f);
    }
    public void showScheduleOnSecondDay(){
        dayOne.setAlpha(0.3f);
        dayOneProgramOne.setVisibility(View.GONE);
        dayOneProgramTwo.setVisibility(View.GONE);
        dayOneProgramThree.setVisibility(View.GONE);
        dayOneProgramFour.setVisibility(View.GONE);
        dayOneProgramFive.setVisibility(View.GONE);
        dayOneProgramSix.setVisibility(View.GONE);
        dayOneProgramSeven.setVisibility(View.GONE);
        dayThree.setAlpha(0.3f);
        dayTwo.setAlpha(1.0f);
    }
    public void showScheduleOnThirdDay(){
        dayOne.setAlpha(0.3f);
        dayTwo.setAlpha(0.3f);
        dayThree.setAlpha(1.0f);
    }
}
