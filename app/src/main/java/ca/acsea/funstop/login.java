package ca.acsea.funstop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private boolean loggedIn;
    public static final String CHANNEL_ID = "ACSEA";
    public static final String CHANNEL_NAME = " ACSEA";
    public static final String CHANNEL_DESC = "ACSEA";
    private EditText editTextEmail;
    private EditText passwordText;
    private FirebaseAuth mAuth;
    private boolean isNewUser;
    private boolean agreedToReceiveEmailIsChecked;
    private boolean agreedToProgramNotification;
    private boolean agreedToJoinBigPrizeIsChecked;
    private String city;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loggedIn = false;
        SharedPreferences prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //setUp(prefs);
        agreedToReceiveEmailIsChecked = prefs.getBoolean("agreedToReceiveEmailIsChecked", false);
        agreedToProgramNotification = prefs.getBoolean("agreedToProgramNotification", false);
        agreedToJoinBigPrizeIsChecked = prefs.getBoolean("agreedToJoinBigPrizeIsChecked", false);
        city = prefs.getString("city",null);


        editTextEmail = findViewById(R.id.loginPageEmail);
        passwordText = findViewById(R.id.loginPagePassword);

        mAuth = FirebaseAuth.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.enableLights(true);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes);

            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }
    private void createUser(){
        final String email = editTextEmail.getText().toString();
        final String password = passwordText.getText().toString();

        if (email.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            Toast.makeText(login.this, "Email Required",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.isEmpty()){
            passwordText.setError("Password Required");
            passwordText.requestFocus();
            Toast.makeText(login.this, "password required" ,Toast.LENGTH_LONG).show();
            return;
        }
        //checkIfEmailVerified();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    checkIfEmailVerified();
                    loggedIn = true;
                    //startProfileActivity();
                }else if(task.getException() !=null) {
                    Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        /*mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startProfileActivity();
                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                userLogin(email, password);
                                loggedIn = true;
                                /*FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String UID = user.getUid();
                                String test = firebaseDatabase.getReference("users").child(UID).child("agreedToJoinBigPrize").toString();
                                Log.d("abcd", test);

                                // if user already exists, get data from database for the subscription.
                                //save the value into the variables

                            }else{
                                if(task.getException() !=null) {
                                    Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });*/

    }
    private void startProfileActivity(){
        Intent intent = new Intent(this, menu.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("loggedIn", loggedIn);
        intent.putExtra("isNewUser", isNewUser);
        intent.putExtra("agreedToReceiveEmailIsChecked", agreedToReceiveEmailIsChecked);
        intent.putExtra("agreedToProgramNotification", agreedToProgramNotification);
        intent.putExtra("agreedToJoinBigPrizeIsChecked", agreedToJoinBigPrizeIsChecked);
        startActivity(intent);
    }
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor sharePreference =getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        sharePreference.apply();
    }
    private void userLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    isNewUser = false;
                    startProfileActivity();
                }else{
                    if(task.getException()!=null) {
                        Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void startTorPirvacyPolicyActivity(View view){
        Intent intent = new Intent(this, privacypolicy.class);
        intent.putExtra("loginStatus", loggedIn);
        startActivity(intent);
    }
    public void startTorTermOfUse(View view){
        Intent intent = new Intent(this, termofuse.class);
        intent.putExtra("loginStatus", loggedIn);
        startActivity(intent);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startProfileActivity();
        }
    }
    public void startRegisterActivity(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.

            Toast.makeText(login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            startProfileActivity();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(login.this, "Email not verified", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }
}
