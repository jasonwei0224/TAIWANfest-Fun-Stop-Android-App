package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
public class vanNavMenu extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_nav_menu);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.full_page_ad_vancouver));
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
    public void onBackPressed() {
        Intent intent = new Intent(this, menu.class);
        intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
    public void startAboutActivity(View view){


        Intent intent = new Intent(this, aboutacsea.class);
        startActivity(intent);
    }
    public void startVanFunstopActivity(View view){
        Intent intent = new Intent(this, rule.class);
        startActivity(intent);
    }

    public void startVanVenueMapActivity(View view){
        Intent intent = new Intent(this, map.class);
        startActivity(intent);
    }

    public void startVanSponsorActivity(View view){
        Intent intent = new Intent(this, sponsor.class);
        startActivity(intent);
    }

    public void strartVanScheduleActivity(View view){
        Intent intent = new Intent(this, schedule.class);
        startActivity(intent);
    }

    public void startPrivacyPolicyActivity(View view){
        Intent intent = new Intent(this, privacypolicy.class);
        intent.putExtra("loginStatus", true);
        startActivity(intent);
    }

    public void startTermOfUse(View view){
        Intent intent = new Intent(this, termofuse.class);
       intent.putExtra("loginStatus", true);
        startActivity(intent);
    }
}
