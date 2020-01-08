package ca.acsea.funstop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
   // private boolean vanStationTenComplete;
   // private boolean vanStationElevenComplete;

    private AdView mAdView;

    private TableLayout vanStationOne;
    private TableLayout vanStationTwo;
    private TableLayout vanStationThree;
    private TableLayout vanStationFour;
    private TableLayout vanStationFive;
    private TableLayout vanStationSix;
    private TableLayout vanStationSeven;
    private TableLayout vanStationEight;
    private TableLayout vanStationNine;
   // private TableLayout vanStationTen;
   // private TableLayout vanStationEleven;

    private String vanStationOneCompleteKey = "vanStationOneComplete" ;
    private String vanStationTwoCompleteKey =  "vanStationTwoComplete";
    private String vanStationThreeCompleteKey = "vanStationThreeComplete";
    private String vanStationFourCompleteKey = "vanStationFourComplete";
    private String vanStationFiveCompleteKey = "vanStationFiveComplete";
    private String vanStationSixCompleteKey = "vanStationSixComplete";
    private String vanStationSevenCompleteKey = "vanStationSevenComplete";
    private String vanStationEightCompleteKey = "vanStationEightComplete";
    private String vanStationNineCompleteKey = "vanStationNineComplete";
    // private String vanStationTenCompleteKey = "vanStationTenComplete";
   // private String vanStationElevenCompleteKey = "van stationElevenComplete";

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;

    private boolean isNewUser = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_fun_stop);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAuth = FirebaseAuth.getInstance();
        ScrollView scrollView = findViewById(R.id.vanfunstop);
        scrollView.setVisibility(View.VISIBLE);
        TextView completed = findViewById(R.id.vancompleted);
        completed.setVisibility(View.GONE);
        vanStationOne = findViewById(R.id.vanProgramOne);
        vanStationTwo = findViewById(R.id.vanProgramTwo);
        vanStationThree = findViewById(R.id.vanProgramThree);
        vanStationFour = findViewById(R.id.vanProgramFour);
        vanStationFive = findViewById(R.id.vanProgramFive);
        vanStationSix = findViewById(R.id.vanProgramSix);
        vanStationSeven = findViewById(R.id.vanProgramSeven);
        vanStationEight = findViewById(R.id.vanProgramEight);
        vanStationNine = findViewById(R.id.vanProgramNine);
        //vanStationTen = findViewById(R.id.vanProgramTen);
        //vanStationEleven = findViewById(R.id.vanProgramNine);
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
    }

    private void gameComplete(){
        if(vanStationOneComplete && vanStationTwoComplete && vanStationThreeComplete
                && vanStationFourComplete && vanStationFiveComplete && vanStationSixComplete &&
                vanStationSevenComplete && vanStationEightComplete && vanStationNineComplete){
            ScrollView scrollView = findViewById(R.id.vanfunstop);
            scrollView.setVisibility(View.GONE);
            ImageButton camera = findViewById(R.id.camerabtn);
            camera.setAlpha(0.3f);
            camera.setClickable(false);
            TextView completed = findViewById(R.id.vancompleted);
            completed.setVisibility(View.VISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            String uid = mAuth.getCurrentUser().getUid();
            mDatabase.child("users").child(uid).child("Complete Fun Stop").setValue("YES");
            //Button b = findViewById(R.id.finish);
            //b.setVisibility(View.VISIBLE);
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
       /* if(prefs.contains(vanStationTenCompleteKey)){
            vanStationTenComplete = prefs.getBoolean(vanStationTenCompleteKey, false);
            if(vanStationTenComplete) {
                setStationComplete(vanStationTen);
            }
        }
        if(prefs.contains(vanStationElevenCompleteKey)){
            vanStationElevenComplete = prefs.getBoolean(vanStationElevenCompleteKey, false);
            if(vanStationElevenComplete){
                setStationComplete(vanStationEleven);
            }
        }*/
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
            if(vanStationOneComplete && vanStationTwoComplete && vanStationThreeComplete
                    && vanStationFourComplete && vanStationFiveComplete && vanStationSixComplete &&
                    vanStationSevenComplete && vanStationEightComplete) {
                vanStationNineComplete = true;
                vanStationNine.setAlpha(0.3f);
            }else {
                Toast.makeText(VanFunStop.this, "Please come back when you've visit all other locations", Toast.LENGTH_LONG).show();
            }
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

    }

    public void showRule(View view){
        Intent intent = new Intent(this, rule.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, vanNavMenu.class);
        intent.putExtra("isNewUser",isNewUser);
        startActivity(intent);
        return;
    }
}
