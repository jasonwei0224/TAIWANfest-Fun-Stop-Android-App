package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class Profile extends AppCompatActivity {
    public static final String NODE_USERS = "users";
    private FirebaseAuth mAuth;
    //private FirebaseAnalytics mFirebaseAnalytics;
    private boolean agreedToSubscribe;
    private boolean agreedToJoinBigPrize;
    private boolean agreedToRecieveEmail;
    private String choiceOfCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            agreedToJoinBigPrize = bundle.getBoolean("agreedToJoinBigPrize");
            agreedToSubscribe = bundle.getBoolean("agreedToSubscribe");
            agreedToRecieveEmail = bundle.getBoolean("agreedToRecieveEmail");
            choiceOfCity = bundle.getString("choiceOfCity");
        }
        // mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        mAuth = FirebaseAuth.getInstance();
        if (agreedToSubscribe && choiceOfCity.equalsIgnoreCase("Toronto")) {
            FirebaseMessaging.getInstance().subscribeToTopic("TAIWANfest");
            FirebaseMessaging.getInstance().subscribeToTopic("Toronto");
        } else if (agreedToSubscribe && choiceOfCity.equalsIgnoreCase("Vancouver")) {
            FirebaseMessaging.getInstance().subscribeToTopic("TAIWANfest");
            FirebaseMessaging.getInstance().subscribeToTopic("Vancouver");
        } else {
        }
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();
                            saveToken(token);
                        } else {
                        }
                    }
                });
    }

    private void saveToken(String token) {
        String email = mAuth.getCurrentUser().getEmail();
        User user = new User(email, token, agreedToRecieveEmail, agreedToSubscribe, agreedToJoinBigPrize, choiceOfCity);

        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference(NODE_USERS);
        dbUser.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Token Saved", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, menu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            intent.putExtra("agreedToRecieveEmail", agreedToRecieveEmail);
            intent.putExtra("agreedToSubscribe", agreedToSubscribe);
            intent.putExtra("agreedToJoinBigPrize", agreedToJoinBigPrize);
            intent.putExtra("choiceOfCity", choiceOfCity);
        }
    }
}