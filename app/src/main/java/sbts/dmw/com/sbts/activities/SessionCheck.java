package sbts.dmw.com.sbts.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Objects;

import sbts.dmw.com.sbts.R;
import sbts.dmw.com.sbts.classes.SessionManager;

public class SessionCheck extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_check);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogIn();

        HashMap<String, String> user = sessionManager.getUserDetails();
        String sEmail = user.get(SessionManager.EMAIL);
        String sRole = user.get(SessionManager.ROLE);

        if(sRole!=null){
            if(sRole.contains("Attendee")){
                Intent attendee = new Intent(SessionCheck.this, Attendee.class);
                attendee.putExtra("email",sEmail);
                startActivity(attendee);
                finish();
            }else{
                Intent parent = new Intent(SessionCheck.this, MapsActivity.class);
                startActivity(parent);
                finish();
            }
        }
    }
}


/*
Intent intent = getIntent();
        String username = intent.getStringExtra("EMAIL");
        String role = intent.getStringExtra("ROLE");
        if(role.contains("Attendee")){
            Intent attendee = new Intent(SessionCheck.this, Attendee.class);
            attendee.putExtra("email",username);
            startActivity(attendee);
            finish();
        }else{
            Intent parent = new Intent(SessionCheck.this, MapsActivity.class);
            startActivity(parent);
            finish();
        }
 */