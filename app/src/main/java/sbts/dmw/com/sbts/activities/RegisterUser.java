package sbts.dmw.com.sbts.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import sbts.dmw.com.sbts.R;

public class RegisterUser extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        textView = findViewById(R.id.role_selection);
        String text = "Please select your role:";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs,0,24,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

    }

    public void RegisterParent(View view){
        Intent parentReg = new Intent(getApplicationContext(),registerParent.class);
        startActivity(parentReg);
    }

    public void RegisterAttendee(View view){
        Intent attendeeReg = new Intent(getApplicationContext(),registerAttendee.class);
        startActivity(attendeeReg);
    }
}
