package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class torontoNavMenue extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toronto_nav_menue);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.full_page_ad_toronto));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

            }

            @Override
            public void onAdLoaded() {
                // mAdIsLoading = false;
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //mAdIsLoading = false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, menu.class);
        intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
    public void startTorAboutActivity(View view){
        Intent intent = new Intent(this, torAbout.class);
        startActivity(intent);
    }

    public void startTorFunstopActivity(View view){
        Intent intent = new Intent(this, torontoRule.class);
        startActivity(intent);
    }

    public void startTorVenueMapActivity(View view){
        Intent intent = new Intent(this, torontoVenueMap.class);
        startActivity(intent);
    }

    public void startTorSponsorActivity(View view){
        Intent intent = new Intent(this, torontoSponsor.class);
        startActivity(intent);
    }

    public void strartTorScheduleActivity(View view){
        Intent intent = new Intent(this, torontoSchedule.class);
        startActivity(intent);
    }

    public void startTorPirvacyPolicyActivity(View view){
        Intent intent = new Intent(this, torPrivacyPolicy.class);
        startActivity(intent);
    }

    public void startTorTermOfUse(View view){
        Intent intent = new Intent(this, torTermOfUse.class);
        startActivity(intent);
    }
}
