package ca.acsea.funstop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class termofuse extends AppCompatActivity {
    private boolean loggedIn;
    private ImageButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termofuse);

    Bundle bundle = getIntent().getExtras();
        loggedIn = bundle.getBoolean("loginStatus",false);
    backbtn = findViewById(R.id.termOfUseBackBtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(loggedIn){
                startVanNavMenuActivity(v);
            }
            else{
                startLogInActivity(v);
            }
        }
    });

}

    public void startLogInActivity(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    public void startVanNavMenuActivity(View view)
    {
        Intent intent = new Intent(this, vanNavMenu.class);
        startActivity(intent);
    }
}
