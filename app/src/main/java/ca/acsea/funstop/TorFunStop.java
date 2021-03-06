package ca.acsea.funstop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TorFunStop extends AppCompatActivity {
    private boolean torStationOneComplete;
    private boolean torStationTwoComplete;
    private boolean torStationThreeComplete;
    private boolean torStationFourComplete;
    private boolean torStationFiveComplete;
    private boolean torStationSixComplete;
    private boolean torStationSevenComplete;
    private boolean torStationEightComplete;
    private boolean torStationNineComplete;
    private boolean torStationTenComplete;
    private boolean torStationElevenComplete;

    private TableLayout torStationOne;
    private TableLayout torStationTwo;
    private TableLayout torStationThree;
    private TableLayout torStationFour;
    private TableLayout torStationFive;
    private TableLayout torStationSix;
    private TableLayout torStationSeven;
    private TableLayout torStationEight;
    private TableLayout torStationNine;
    private TableLayout torStationTen;
    private TableLayout torStationEleven;

    private String stationOneCompleteKey = "torStationOneComplete" ;
    private String stationTwoCompleteKey =  "torStationTwoComplete";
    private String stationThreeCompleteKey = "torStationThreeComplete";
    private String stationFourCompleteKey = "torStationFourComplete";
    private String stationFiveCompleteKey = "torStationFiveComplete";
    private String stationSixCompleteKey = "torStationSixComplete";
    private String stationSevenCompleteKey = "torStationSevenComplete";
    private String stationEightCompleteKey = "torStationEightComplete";
    private String stationNineCompleteKey = "torStationNineComplete";
    private String stationTenCompleteKey = "torStationTenComplete";
    private String stationElevenCompleteKey = "torStationElevenComplete";

    private TextView torNotificationTitle;
    private TextView torNotificationBody;
    private String title;
    private String body;
    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;

    private boolean funStopComplete;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fun_stop);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        torStationOne = findViewById(R.id.TorProgramOne);
        torStationTwo = findViewById(R.id.TorProgramTwo);
        torStationThree = findViewById(R.id.TorProgramThree);
        torStationFour = findViewById(R.id.TorProgramFour);
        torStationFive = findViewById(R.id.TorProgramFive);
        torStationSix = findViewById(R.id.TorProgramSix);
       // torStationSeven = findViewById(R.id.TorProgramSeven);
        //torStationEight = findViewById(R.id.TorProgramEight);
       // torStationNine = findViewById(R.id.TorProgramNine);
       // torStationTen = findViewById(R.id.TorProgramTen);
      //  torStationEleven = findViewById(R.id.TorProgramEleven);
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
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastHandler, new IntentFilter("ca.acsea.funstop_message"));


        if(getIntent().getExtras() != null){
            for(String key: getIntent().getExtras().keySet()){
                if(key.equals("title")){
                    title = getIntent().getExtras().getString("title");
                    torNotificationTitle.setText(getIntent().getExtras().getString("title"));
                    torNotificationTitle.setVisibility(View.VISIBLE);
                   // torNotificationTitle.setVisibility(View.VISIBLE);
                }
                else if(key.equals("body")){
                    body = getIntent().getExtras().getString("body");
                    torNotificationBody.setText(getIntent().getExtras().getString("body"));
                    torNotificationBody.setVisibility(View.VISIBLE);
                    //torNotificationBody.setVisibility(View.VISIBLE);
                }
            }
        }

    }
    private void setUp(SharedPreferences prefs){
        if(prefs.contains(stationOneCompleteKey)){
            torStationOneComplete = prefs.getBoolean(stationOneCompleteKey, false);
            if(torStationOneComplete){
                setStationComplete(torStationOne);
            }
        }
        if(prefs.contains(stationTwoCompleteKey)){
            torStationTwoComplete = prefs.getBoolean(stationTwoCompleteKey, false);
            if(torStationTwoComplete){
                setStationComplete(torStationTwo);
            }
        }
        if(prefs.contains(stationThreeCompleteKey)){
            torStationThreeComplete = prefs.getBoolean(stationThreeCompleteKey, false);
            if(torStationThreeComplete){
                setStationComplete(torStationThree);
            }
        }
        if(prefs.contains(stationFourCompleteKey)){
            torStationFourComplete = prefs.getBoolean(stationFourCompleteKey, false);
            if(torStationFourComplete){
                setStationComplete(torStationFour);
            }
        }
        if(prefs.contains(stationFiveCompleteKey)){
            torStationFiveComplete = prefs.getBoolean(stationFiveCompleteKey, false);
            if(torStationFiveComplete){
                setStationComplete(torStationFive);
            }
        }
        if(prefs.contains(stationSixCompleteKey)){
            torStationSixComplete = prefs.getBoolean(stationSixCompleteKey, false);
            if(torStationSixComplete){
                setStationComplete(torStationSix);
            }
        }
        /*if(prefs.contains(stationSevenCompleteKey)){
            torStationSevenComplete = prefs.getBoolean(stationSevenCompleteKey, false);
            if(torStationSevenComplete){
                setStationComplete(torStationSeven);
            }
        }
        if(prefs.contains(stationEightCompleteKey)){
            torStationEightComplete = prefs.getBoolean(stationEightCompleteKey, false);
            if(torStationEightComplete){
                setStationComplete(torStationEight);
            }
        }
        if(prefs.contains(stationNineCompleteKey)){
            torStationNineComplete = prefs.getBoolean(stationNineCompleteKey, false);
            if(torStationNineComplete){
                setStationComplete(torStationNine);
            }
        }
        if(prefs.contains(stationTenCompleteKey)){
            torStationTenComplete = prefs.getBoolean(stationTenCompleteKey, false);
            if(torStationTenComplete) {
                setStationComplete(torStationTen);
            }
        }
        if(prefs.contains(stationElevenCompleteKey)){
            torStationElevenComplete = prefs.getBoolean(stationElevenCompleteKey, false);
            if(torStationElevenComplete){
                setStationComplete(torStationEleven);
            }
        }*/
    }
    public void setStationComplete(TableLayout tableLayout){
        tableLayout.setAlpha(0.3f);
    }

    public void startTorFunStopMapActivity(View view){
        Intent intent = new Intent(this, torontoFunStopMap.class);
        startActivity(intent);
    }

    public void startQRCodeScannerActivity(View view){
        Intent intent = new Intent(this, torCamera.class);
        startActivity(intent);
    }

    public void startTorNavMenueActivity(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);
        startActivity(intent);
    }

    public void gameComplete(){
        if(torStationOneComplete && torStationTwoComplete && torStationThreeComplete
                && torStationFourComplete && torStationFiveComplete && torStationSixComplete){
            ScrollView scrollView = findViewById(R.id.torFunStop);
            scrollView.setVisibility(View.GONE);
            ImageButton camera = findViewById(R.id.torcamerabtn);
            camera.setAlpha(0.3f);
            camera.setClickable(false);
            funStopComplete = true;
            TextView completed = findViewById(R.id.torcompleted);
            completed.setVisibility(View.VISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            String uid = mAuth.getCurrentUser().getUid();
            mDatabase.child("users").child(uid).child("Complete Fun Stop").setValue("YES");
            //Button b = findViewById(R.id.finish);
            //b.setVisibility(View.VISIBLE);
        }
        if(torStationOneComplete && torStationTwoComplete && torStationThreeComplete
        && torStationFourComplete && torStationFiveComplete && torStationSixComplete &&
                torStationSevenComplete && torStationEightComplete &&torStationNineComplete
                && torStationTenComplete&& torStationElevenComplete){
            ScrollView scrollView = findViewById(R.id.torFunStop);
            scrollView.setVisibility(View.GONE);
            ImageButton camera = findViewById(R.id.torcamerabtn);
            camera.setAlpha(0.3f);
            camera.setClickable(false);
            funStopComplete = true;
            TextView completed = findViewById(R.id.torcompleted);
            completed.setVisibility(View.VISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            String uid = mAuth.getCurrentUser().getUid();
            mDatabase.child("users").child(uid).child("Complete Fun Stop").setValue("YES");
            //Button b = findViewById(R.id.finish);
            //b.setVisibility(View.VISIBLE);
        }
    }

    public void updateProgramCompletion(int number){
        if(number == 1){
            torStationOneComplete = true;
            torStationOne.setAlpha(0.3f);
        } else if (number == 2){
            torStationTwoComplete = true;
            torStationTwo.setAlpha(0.3f);
        } else if (number == 3){
            torStationThreeComplete = true;
            torStationThree.setAlpha(0.3f);
        } else if (number == 4){
            torStationFourComplete = true;
            torStationFour.setAlpha(0.3f);
        } else if (number == 5){
            torStationFiveComplete = true;
            torStationFive.setAlpha(0.3f);
        } else if (number == 6) {
            if (torStationOneComplete && torStationTwoComplete && torStationThreeComplete
                    && torStationFourComplete && torStationFiveComplete) {
                torStationSixComplete = true;
                torStationSix.setAlpha(0.3f);
            } else {
                Toast.makeText(TorFunStop.this, "Please come back when you've visit all other locations", Toast.LENGTH_LONG).show();

            }/* else if (number == 7){
            torStationSevenComplete = true;
            torStationSeven.setAlpha(0.3f);
        } else if (number == 8){
            torStationEightComplete = true;
            torStationEight.setAlpha(0.3f);
        } else if (number == 9){
            torStationNineComplete = true;
            torStationNine.setAlpha(0.3f);
        } else if (number == 10){
            torStationTenComplete = true;
            torStationTen.setAlpha(0.3f);
        } else if (number == 11){
            if(torStationOneComplete && torStationTwoComplete && torStationThreeComplete
                    && torStationFourComplete && torStationFiveComplete && torStationSixComplete &&
                    torStationSevenComplete && torStationEightComplete &&torStationNineComplete
                    && torStationTenComplete) {
                torStationElevenComplete = true;
                torStationEleven.setAlpha(0.3f);
            }else{
                Toast.makeText(TorFunStop.this, "Please come back when you've visit all other locations", Toast.LENGTH_LONG).show();
            }
        }*/
        }
    }

    public void onPause() {
        super.onPause();
        SharedPreferences.Editor prefEditor = getSharedPreferences("toronto", Context.MODE_PRIVATE).edit();
        prefEditor.putBoolean(stationOneCompleteKey, torStationOneComplete);
        prefEditor.putBoolean(stationTwoCompleteKey, torStationTwoComplete);
        prefEditor.putBoolean(stationThreeCompleteKey, torStationThreeComplete);
        prefEditor.putBoolean(stationFourCompleteKey, torStationFourComplete);
        prefEditor.putBoolean(stationFiveCompleteKey, torStationFiveComplete);
        prefEditor.putBoolean(stationSixCompleteKey, torStationSixComplete);
     //   prefEditor.putBoolean(stationSevenCompleteKey, torStationSevenComplete);
     //   prefEditor.putBoolean(stationEightCompleteKey, torStationEightComplete);
      //  prefEditor.putBoolean(stationNineCompleteKey, torStationNineComplete);
      //  prefEditor.putBoolean(stationTenCompleteKey, torStationTenComplete);
      //  prefEditor.putBoolean(stationElevenCompleteKey, torStationElevenComplete);
        prefEditor.putBoolean("funStopComplete", funStopComplete);
        prefEditor.apply();
    }

    public void missionComplete(){

        ScrollView scrollView = findViewById(R.id.torFunStop);
        scrollView.setVisibility(View.GONE);
        ImageButton camera = findViewById(R.id.torcamerabtn);
        camera.setAlpha(0.3f);
        camera.setClickable(false);
        TextView completed = findViewById(R.id.torcompleted);
        completed.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(mAuth.getCurrentUser().getUid()).child("Complete Fun Stop").setValue("YES");
        torStationOneComplete = false;
        torStationTwoComplete = false;
        torStationThreeComplete = false;
        torStationFourComplete = false;
        torStationFiveComplete = false;
        torStationSixComplete = false;
      //  torStationSevenComplete = false;
        //torStationEightComplete = false;
        //torStationNineComplete = false;
        //torStationTenComplete = false;
        //torStationElevenComplete = false;
    }

    private BroadcastReceiver broadcastHandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            title = intent.getStringExtra("title");
            body = intent.getStringExtra("body");
            torNotificationTitle.setText(title);
            torNotificationBody.setText(body);
            torNotificationTitle.setVisibility(View.VISIBLE);
            torNotificationBody.setVisibility(View.VISIBLE);
          //  notification_background.setVisibility(View.VISIBLE);
        }
    };

    public void showRules(View view){
        Intent intent = new Intent(this, torontoRule.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
      /*  if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }*/
        Intent intent = new Intent(this, torontoNavMenue.class);
        intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }

}
