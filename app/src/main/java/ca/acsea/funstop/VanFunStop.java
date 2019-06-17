package ca.acsea.funstop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

public class VanFunStop extends AppCompatActivity {
    private boolean vanStationOneComplete;
    private boolean vanStationTwoComplete;
    private boolean vanStationThreeComplete;
    private boolean vanStationFourComplete;
    private boolean vanStationFiveComplete;
    private boolean vanStationSixComplete;
    private boolean vanStationSevenComplete;
    private boolean vanStationEightComplete;
    private boolean vanStationNineComplete;
    private boolean vanStationTenComplete;

    private TableLayout vanStationOne;
    private TableLayout vanStationTwo;
    private TableLayout vanStationThree;
    private TableLayout vanStationFour;
    private TableLayout vanStationFive;
    private TableLayout vanStationSix;
    private TableLayout vanStationSeven;
    private TableLayout vanStationEight;
    private TableLayout vanStationNine;
    private TableLayout vanStationTen;

    private String vanStationOneCompleteKey = "vanStationOneComplete" ;
    private String vanStationTwoCompleteKey =  "vanStationTwoComplete";
    private String vanStationThreeCompleteKey = "vanStationThreeComplete";
    private String vanStationFourCompleteKey = "vanStationFourComplete";
    private String vanStationFiveCompleteKey = "vanStationFiveComplete";
    private String vanStationSixCompleteKey = "vanStationSixComplete";
    private String vanStationSevenCompleteKey = "vanStationSevenComplete";
    private String vanStationEightCompleteKey = "vanStationEightComplete";
    private String vanStationNineCompleteKey = "vanStationNineComplete";
    private String vanStationTenCompleteKey = "vanStationTenComplete";

    private TextView vanNotificationTitle;
    private TextView vanNotificationBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_fun_stop);

        vanStationOne = findViewById(R.id.vanProgramOne);
        vanStationTwo = findViewById(R.id.vanProgramTwo);
        vanStationThree = findViewById(R.id.vanProgramThree);
        vanStationFour = findViewById(R.id.vanProgramFour);
        vanStationFive = findViewById(R.id.vanProgramFive);
        vanStationSix = findViewById(R.id.vanProgramSix);
        vanStationSeven = findViewById(R.id.vanProgramSeven);
        vanStationEight = findViewById(R.id.vanProgramEight);
        vanStationNine = findViewById(R.id.vanProgramNine);
        vanStationTen = findViewById(R.id.vanProgramTen);
        SharedPreferences prefs = getSharedPreferences("toronto", Context.MODE_PRIVATE);
        setUp(prefs);
        gameComplete();
        Intent intent = getIntent();
        Barcode barcode = getIntent().getParcelableExtra("barcode");
        try{
            int stationID = Integer.parseInt(barcode.rawValue);
            updateProgramCompletion(stationID);
        }catch (Exception e){

        }
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastHandler, new IntentFilter("ca.acsea.myapplication_message"));
        vanNotificationTitle = findViewById(R.id.vanNotificationTitle);
        vanNotificationBody = findViewById(R.id.vanNotificationBody);
        if(getIntent().getExtras() != null){
            for(String key: getIntent().getExtras().keySet()){
                if(key.equals("title")){
                    vanNotificationTitle.setText(getIntent().getExtras().getString("title"));
                    vanNotificationTitle.setVisibility(View.VISIBLE);
                    // torNotificationTitle.setVisibility(View.VISIBLE);
                }
                else if(key.equals("body")){
                    vanNotificationBody.setText(getIntent().getExtras().getString("body"));
                    vanNotificationBody.setVisibility(View.VISIBLE);
                    //torNotificationBody.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private BroadcastReceiver broadcastHandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String title = intent.getStringExtra("title");
            String body = intent.getStringExtra("body");
            vanNotificationTitle.setText(title);
            vanNotificationBody.setText(body);
            vanNotificationTitle.setVisibility(View.VISIBLE);
            vanNotificationBody.setVisibility(View.VISIBLE);
            //  notification_background.setVisibility(View.VISIBLE);
        }
    };

    private void gameComplete(){
        if(vanStationOneComplete && vanStationTwoComplete && vanStationThreeComplete
                && vanStationFourComplete && vanStationFiveComplete && vanStationSixComplete &&
                vanStationSevenComplete && vanStationEightComplete && vanStationNineComplete
                && vanStationTenComplete){
            Button b = findViewById(R.id.finish);
            b.setVisibility(View.VISIBLE);
        }
    }
    private void setUp(SharedPreferences prefs){
        if(prefs.contains(vanStationOneCompleteKey)){
            vanStationOneComplete = prefs.getBoolean(vanStationOneCompleteKey, false);
            if(vanStationOneComplete){
                setStationComplete(vanStationOne);
            }
        }
        if(prefs.contains(vanStationTwoCompleteKey)){
            vanStationTwoComplete = prefs.getBoolean(vanStationTwoCompleteKey, false);
            if(vanStationTwoComplete){
                setStationComplete(vanStationTwo);
            }
        }
        if(prefs.contains(vanStationThreeCompleteKey)){
            vanStationThreeComplete = prefs.getBoolean(vanStationThreeCompleteKey, false);
            if(vanStationThreeComplete){
                setStationComplete(vanStationThree);
            }
        }
        if(prefs.contains(vanStationFourCompleteKey)){
            vanStationFourComplete = prefs.getBoolean(vanStationFourCompleteKey, false);
            if(vanStationFourComplete){
                setStationComplete(vanStationFour);
            }
        }
        if(prefs.contains(vanStationFiveCompleteKey)){
            vanStationFiveComplete = prefs.getBoolean(vanStationFiveCompleteKey, false);
            if(vanStationFiveComplete){
                setStationComplete(vanStationFive);
            }
        }
        if(prefs.contains(vanStationSixCompleteKey)){
            vanStationSixComplete = prefs.getBoolean(vanStationSixCompleteKey, false);
            if(vanStationSixComplete){
                setStationComplete(vanStationSix);
            }
        }
        if(prefs.contains(vanStationSevenCompleteKey)){
            vanStationSevenComplete = prefs.getBoolean(vanStationSevenCompleteKey, false);
            if(vanStationSevenComplete){
                setStationComplete(vanStationSeven);
            }
        }
        if(prefs.contains(vanStationEightCompleteKey)){
            vanStationEightComplete = prefs.getBoolean(vanStationEightCompleteKey, false);
            if(vanStationEightComplete){
                setStationComplete(vanStationEight);
            }
        }
        if(prefs.contains(vanStationNineCompleteKey)){
            vanStationNineComplete = prefs.getBoolean(vanStationNineCompleteKey, false);
            if(vanStationNineComplete){
                setStationComplete(vanStationNine);
            }
        }
        if(prefs.contains(vanStationTenCompleteKey)){
            vanStationTenComplete = prefs.getBoolean(vanStationTenCompleteKey, false);
            if(vanStationTenComplete) {
                setStationComplete(vanStationTen);
            }
        }
    }

    private void setStationComplete(TableLayout tableLayout){
        tableLayout.setAlpha(0.3f);

    }
    public void startVanFunStopMapActivity(View view){
        Intent intent = new Intent(this, vanFunStopMap.class);
        startActivity(intent);
    }

    public void startQRCodeScannerActivity(View view){
        Intent intent = new Intent(this, vanCamera.class);
        startActivity(intent);
    }
    public void startVanNavMenuActivity(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }



    private void updateProgramCompletion(int stationNumber){
        if(stationNumber == 1){
            vanStationOneComplete = true;
            vanStationOne.setAlpha(0.3f);
        } else if (stationNumber == 2){
            vanStationTwoComplete = true;
            vanStationTwo.setAlpha(0.3f);
        } else if (stationNumber == 3){
            vanStationThreeComplete = true;
            vanStationThree.setAlpha(0.3f);
        } else if (stationNumber == 4){
            vanStationFourComplete = true;
            vanStationFour.setAlpha(0.3f);
        } else if (stationNumber == 5){
            vanStationFiveComplete = true;
            vanStationFive.setAlpha(0.3f);
        } else if (stationNumber == 6){
            vanStationSixComplete = true;
            vanStationSix.setAlpha(0.3f);
        } else if (stationNumber == 7){
            vanStationSevenComplete = true;
            vanStationSeven.setAlpha(0.3f);
        } else if (stationNumber == 8){
            vanStationEightComplete = true;
            vanStationEight.setAlpha(0.3f);
        } else if (stationNumber == 9){
            vanStationNineComplete = true;
            vanStationNine.setAlpha(0.3f);
        } else if (stationNumber == 10){
            vanStationTenComplete = true;
            vanStationTen.setAlpha(0.3f);
        }
    }
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor prefEditor = getSharedPreferences("toronto", Context.MODE_PRIVATE).edit();
        prefEditor.putBoolean(vanStationOneCompleteKey, vanStationOneComplete);
        prefEditor.putBoolean(vanStationTwoCompleteKey, vanStationTwoComplete);
        prefEditor.putBoolean(vanStationThreeCompleteKey, vanStationThreeComplete);
        prefEditor.putBoolean(vanStationFourCompleteKey, vanStationFourComplete);
        prefEditor.putBoolean(vanStationFiveCompleteKey, vanStationFiveComplete);
        prefEditor.putBoolean(vanStationSixCompleteKey, vanStationSixComplete);
        prefEditor.putBoolean(vanStationSevenCompleteKey, vanStationSevenComplete);
        prefEditor.putBoolean(vanStationEightCompleteKey, vanStationEightComplete);
        prefEditor.putBoolean(vanStationNineCompleteKey, vanStationNineComplete);
        prefEditor.putBoolean(vanStationTenCompleteKey, vanStationTenComplete);
        prefEditor.apply();
    }

    public void reset(View view){
        vanStationOneComplete = false;
        vanStationTwoComplete = false;
        vanStationThreeComplete = false;
        vanStationFourComplete = false;
        vanStationFiveComplete = false;
        vanStationSixComplete = false;
        vanStationSevenComplete = false;
        vanStationEightComplete = false;
        vanStationNineComplete = false;
        vanStationTenComplete = false;
    }
    public void showRule(View view){
        Intent intent = new Intent(this, rule.class);
        startActivity(intent);
    }
}
