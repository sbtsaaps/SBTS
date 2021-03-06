package sbts.dmw.com.sbts.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import sbts.dmw.com.sbts.classes.MySingleton;
import sbts.dmw.com.sbts.R;

public class registerAttendee extends AppCompatActivity {

    private EditText first_Name, mid_Name, last_Name, email_Address, phone_Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_attendee);

        TextView textView = findViewById(R.id.regAttendee);
        String text = "Attendee Registration";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs,0,21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        first_Name = findViewById(R.id.firstName);
        mid_Name = findViewById(R.id.midName);
        last_Name = findViewById(R.id.lastName);
        email_Address = findViewById(R.id.emailAddress);
        phone_Number = findViewById(R.id.phoneNumber);

    }

    public void Submit(View view) {
        String url ="https://defcon12.000webhostapp.com/Attendeeload.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().contains("success")){
                            Toast.makeText(getApplicationContext(), getString(R.string.attRegSuccessful),Toast.LENGTH_LONG).show();
                        }else{
                            //Toast.makeText(getApplicationContext(), getString(R.string.attRegFailed),Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Please try later.",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", first_Name.getText().toString());
                params.put("mname", mid_Name.getText().toString());
                params.put("lname", last_Name.getText().toString());
                params.put("email", email_Address.getText().toString());
                params.put("mobileno", phone_Number.getText().toString());
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        finish();
    }
}
