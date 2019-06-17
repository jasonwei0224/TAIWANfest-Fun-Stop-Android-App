package ca.acsea.funstop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class menu extends AppCompatActivity {
    //ImageButton torontobtn = findViewById(R.id.im2);
    //@Override
    private boolean agreedToReceiveEmailIsChecked;
    private boolean agreedToProgramNotification;
    private boolean agreedToJoinBigPrizeIsChecked;
    private String city;
    private FirebaseAuth mAuth;
    public static final String NODE_USERS = "users";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundle = getIntent().getExtras();
        agreedToReceiveEmailIsChecked = bundle.getBoolean("agreedToReceiveEmailIsChecked");
        agreedToProgramNotification = bundle.getBoolean("agreedToProgramNotification");
        agreedToJoinBigPrizeIsChecked = bundle.getBoolean("agreedToJoinBigPrizeIsChecked");
        city = bundle.getString("choiceOfCity");
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
        User user = new User(email, token, agreedToReceiveEmailIsChecked, agreedToProgramNotification, agreedToJoinBigPrizeIsChecked,
                city);

        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference(NODE_USERS);
        dbUser.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(menu.this, "Token Saved", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
