package ca.acsea.funstop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    public static final String CHANNEL_ID = "ACSEA";
    public static final String CHANNEL_NAME = " ACSEA";
    public static final String CHANNEL_DESC = "ACSEA";
    private CheckBox agreeToReceiveEmail;
    private CheckBox agreeToProgramNotification ;
    private CheckBox agreeToJoinPoolOfPrize;
    private EditText editTextEmail;
    private EditText passwordText;
    private Spinner cityChoice;
    private String city;
    private boolean agreedToReceiveEmailIsChecked;
    private boolean agreedToProgramNotification;
    private boolean agreedToJoinBigPrizeIsChecked;
    private FirebaseAuth mAuth;
    private boolean isNewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SharedPreferences prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        setUp(prefs);


        agreeToReceiveEmail = findViewById(R.id.agreeToReceiveEmail);
        agreeToJoinPoolOfPrize = findViewById(R.id.agreeToJoinPoolOfPrize);
        agreeToProgramNotification = findViewById(R.id.agreeToReceiveProgramNotification);
        /*agreedToReceiveEmailIsChecked = agreeToReceiveEmail.isChecked();*/
        editTextEmail = findViewById(R.id.email);
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

       agreeToReceiveEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    agreedToReceiveEmailIsChecked = true;
                    //Change the picture
                    //Toast.makeText(login.this, "checked", Toast.LENGTH_LONG).show();
                }
            }
        });
        agreeToProgramNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    agreedToProgramNotification = true;
                    //Change the picture
                    //Toast.makeText(login.this, "checked", Toast.LENGTH_LONG).show();
                }
            }
        });
        agreeToJoinPoolOfPrize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    agreedToJoinBigPrizeIsChecked = true;
                    //Change the picture
                    //Toast.makeText(login.this, "checked", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.registerbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }
    private void setUp(SharedPreferences preferences){
        if(preferences.contains("agreedToReceiveEmailIsChecked")){
            agreedToReceiveEmailIsChecked = preferences.getBoolean("agreedToReceiveEmailIsChecked", false);
        }
        if(preferences.contains("agreedToProgramNotification")){
            agreedToProgramNotification = preferences.getBoolean("agreedToProgramNotification", false);
        }
        if(preferences.contains("agreedToJoinBigPrizeIsChecked")){
            agreedToJoinBigPrizeIsChecked = preferences.getBoolean("agreedToJoinBigPrizeIsChecked", false);
        }
        if(preferences.contains("city")){
            city = preferences.getString("city", null);
        }
    }
    private void createUser(){
       agreedToReceiveEmailIsChecked = agreeToReceiveEmail.isChecked();
        agreedToJoinBigPrizeIsChecked = agreeToJoinPoolOfPrize.isChecked();
        agreedToProgramNotification = agreeToProgramNotification.isChecked();
        cityChoice = findViewById(R.id.cityChoices);
        city = String.valueOf(cityChoice.getSelectedItem());
        final String email = editTextEmail.getText().toString();
        final String password = passwordText.getText().toString();
        if(!agreedToReceiveEmailIsChecked){
            agreeToReceiveEmail.setError("Must be agree to use our app");
            agreeToReceiveEmail.requestFocus();
            Toast.makeText(Register.this, "must agree to use",Toast.LENGTH_LONG).show();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            Toast.makeText(Register.this, "Email Required",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.isEmpty()){
            passwordText.setError("Password Required");
            passwordText.requestFocus();
            Toast.makeText(Register.this, "password required" ,Toast.LENGTH_LONG).show();
            return;
        }
       if(password.length() < 6){
            passwordText.setError("Password must be longer than 6");
            passwordText.requestFocus();
            Toast.makeText(Register.this, "password must be longer than 6",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            isNewUser = true;
                            //startProfileActivity();
                            sendEmailVerification();
                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                              //  userLogin(email, password);
                                //isNewUser = true;
                                Toast.makeText(Register.this, "Already Registered", Toast.LENGTH_LONG).show();

                                /*FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String UID = user.getUid();
                                String test = firebaseDatabase.getReference("users").child(UID).child("agreedToJoinBigPrize").toString();
                                Log.d("abcd", test);*/

                                // if user already exists, get data from database for the subscription.
                                //save the value into the variables

                            }else{
                                if(task.getException() !=null) {
                                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });


    }
    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startProfileActivity();
        }
    }
    private void startProfileActivity(){
        Intent intent = new Intent(this, menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("agreedToReceiveEmailIsChecked", agreedToReceiveEmailIsChecked);
        intent.putExtra("agreedToProgramNotification", agreedToProgramNotification);
        intent.putExtra("agreedToJoinBigPrizeIsChecked", agreedToJoinBigPrizeIsChecked);
        intent.putExtra("city", city);
        intent.putExtra("isNewUser", isNewUser);
        startActivity(intent);
    }
    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor sharePreference =getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        sharePreference.putBoolean("agreedToReceiveEmailIsChecked", agreedToReceiveEmailIsChecked);
        sharePreference.putBoolean("agreedToProgramNotification", agreedToProgramNotification);
        sharePreference.putBoolean("agreedToJoinBigPrizeIsChecked", agreedToJoinBigPrizeIsChecked);
        sharePreference.putString("city", city);
        sharePreference.putBoolean("isNewUser", isNewUser);
        sharePreference.apply();
    }
    private void userLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startProfileActivity();
                }else{
                    if(task.getException()!=null) {
                        Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void startLoginActivity(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    private void sendEmailVerification(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().signOut();
                            //Intent intent = new Intent(this, login.class);
                            //startActivity(intent);
                            Toast.makeText(Register.this, "Verification Email Sent", Toast.LENGTH_LONG).show();
                        }
                        else
                        {

                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }
}