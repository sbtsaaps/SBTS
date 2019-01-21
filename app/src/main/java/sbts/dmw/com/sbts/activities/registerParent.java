package sbts.dmw.com.sbts.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import sbts.dmw.com.sbts.MySingleton;
import sbts.dmw.com.sbts.R;

public class registerParent extends AppCompatActivity {

    EditText first_Name, mid_Name, last_Name,email_Address, phone_Number, student_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parent);

        first_Name = findViewById(R.id.firstName);
        mid_Name = findViewById(R.id.midName);
        last_Name = findViewById(R.id.lastName);
        email_Address = findViewById(R.id.emailAddress);
        phone_Number = findViewById(R.id.phoneNumber);
        student_ID = findViewById(R.id.studentId);

    }

    public void Submit(View view) {
        String url ="https://defcon12.000webhostapp.com/Attendeeload.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.substring(1,2).contentEquals("0")){
                            Toast.makeText(getApplicationContext(),"Registration Successful!",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Registration Failed.",Toast.LENGTH_LONG).show();
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
                params.put("id", student_ID.getText().toString());
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
