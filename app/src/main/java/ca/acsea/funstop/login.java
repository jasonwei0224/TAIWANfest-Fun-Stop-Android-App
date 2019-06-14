package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private CheckBox agreeToReceiveEmail;
    private CheckBox agreeToProgramNotification ;
    private CheckBox agreeToJoinPoolOfPrize;
    private EditText editTextEmail;
    private Spinner cityChoice;
    private String city;
    private boolean agreedToReceiveEmailIsChecked;
    private boolean agreedToProgramNotification;
    private boolean agreedToJoinBigPrize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        agreeToReceiveEmail = findViewById(R.id.agreeToReceiveEmail);
        agreeToJoinPoolOfPrize = findViewById(R.id.agreeToJoinPoolOfPrize);
        agreeToProgramNotification = findViewById(R.id.agreeToProgramNotification);

        agreedToReceiveEmailIsChecked = agreeToReceiveEmail.isChecked();
        /*if(agreedToReceiveEmailIsChecked){
            Toast.makeText(this, "checked", Toast.LENGTH_LONG).show();
        }*/
        agreeToReceiveEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    agreedToReceiveEmailIsChecked = isChecked;
                    //Change the picture
                    //Toast.makeText(login.this, "checked", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createUser(){
        agreedToJoinBigPrize = agreeToJoinPoolOfPrize.isChecked();
        agreedToProgramNotification = agreeToProgramNotification.isChecked();
        city = String.valueOf(cityChoice.getSelectedItem());
        final String email = editTextEmail.getText().toString();
        if(!agreedToReceiveEmailIsChecked){
            agreeToReceiveEmail.setError("Must be agree to use our app");
            agreeToReceiveEmail.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

    }


}
