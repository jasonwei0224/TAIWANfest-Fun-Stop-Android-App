package ca.acsea.funstop;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class menu extends AppCompatActivity {

    private boolean agreedToReceiveEmailIsChecked;
    private boolean agreedToProgramNotification;
    private boolean agreedToJoinBigPrizeIsChecked;
    private boolean loggedIn;
    private boolean isNewUser;
    private String city;
    private FirebaseAuth mAuth;
    private User user;
    public static final String NODE_USERS = "users";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundle = getIntent().getExtras();
        SharedPreferences prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        isNewUser = bundle.getBoolean("isNewUser", false);
        agreedToReceiveEmailIsChecked = bundle.getBoolean("agreedToReceiveEmailIsChecked");
        agreedToProgramNotification = bundle.getBoolean("agreedToProgramNotification");
        agreedToJoinBigPrizeIsChecked = bundle.getBoolean("agreedToJoinBigPrizeIsChecked");
        city = bundle.getString("city");
        if(isNewUser){
            agreedToReceiveEmailIsChecked = bundle.getBoolean("agreedToReceiveEmailIsChecked");
            agreedToProgramNotification = bundle.getBoolean("agreedToProgramNotification");
            agreedToJoinBigPrizeIsChecked = bundle.getBoolean("agreedToJoinBigPrizeIsChecked");
            city = bundle.getString("city");
        }else{
            if(prefs.contains("agreedToReceiveEmailIsChecked")){
                agreedToReceiveEmailIsChecked = prefs.getBoolean("agreedToReceiveEmailIsChecked", false);
            }
            if(prefs.contains("agreedToProgramNotification")){
                agreedToProgramNotification = prefs.getBoolean("agreedToProgramNotification", false);
            }
            if(prefs.contains("agreedToJoinBigPrizeIsChecked")){
                agreedToJoinBigPrizeIsChecked = prefs.getBoolean("agreedToJoinBigPrizeIsChecked", false);
            }
        }

        mAuth = FirebaseAuth.getInstance();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();
                            saveToken(token);
                        }
                    }
                });

    }
    public void startVanNavMenu(View view){
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }
    public void startTorNavMenu(View view){
        Intent intent = new Intent(this, torontoNavMenue.class);

        startActivity(intent);
    }
    private void saveToken(String token){
        String email = mAuth.getCurrentUser().getEmail();
        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference(NODE_USERS);
        String uid = mAuth.getCurrentUser().getUid();
        if(!isNewUser){
            user = new User(email, token, agreedToReceiveEmailIsChecked, agreedToProgramNotification, agreedToJoinBigPrizeIsChecked,
                    city);

        }else {
            user = new User(email, token, agreedToReceiveEmailIsChecked, agreedToProgramNotification, agreedToJoinBigPrizeIsChecked,
                    city);
        }

        dbUser.child(uid).child("agreeToJoinBigPrizeIsChecked").setValue(agreedToJoinBigPrizeIsChecked);
        dbUser.child(uid).child("agreeToProgramNotification").setValue(agreedToProgramNotification);
        dbUser.child(uid).child("agreeToReceiveEmailIsChecked").setValue(agreedToReceiveEmailIsChecked);
        dbUser.child(uid).child("email").setValue(email);
        dbUser.child(uid).child("city").setValue(city);
        dbUser.child(uid).child("token").setValue(token);
    }
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return;
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor prefEditor = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        prefEditor.putBoolean("agreedToReceiveEmailIsChecked", agreedToReceiveEmailIsChecked);
        prefEditor.putBoolean("agreedToProgramNotification", agreedToProgramNotification);
        prefEditor.putBoolean("agreedToJoinBigPrizeIsChecked", agreedToJoinBigPrizeIsChecked);
        prefEditor.apply();

    }
}
