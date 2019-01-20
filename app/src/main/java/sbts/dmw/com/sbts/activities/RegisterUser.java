package sbts.dmw.com.sbts.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import sbts.dmw.com.sbts.R;

public class RegisterUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText fname,lname,email,pass,mobile,username;

    Spinner role;
    String roleOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        TextView textView = findViewById(R.id.textView3);
        String text = "Registration Form";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs,0,17,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        mobile = findViewById(R.id.mobileno);
        username = findViewById(R.id.username);
        role = findViewById(R.id.role);

        ArrayAdapter<CharSequence> adpt = ArrayAdapter.createFromResource(this, R.array.Role, android.R.layout.simple_spinner_item);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adpt);
        role.setOnItemSelectedListener(this);
    }

    public void Submit(View view){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://defcon12.000webhostapp.com/insert.php";

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
                params.put("fname", fname.getText().toString());
                params.put("lname", lname.getText().toString());
                params.put("email", email.getText().toString());
                params.put("mobileno", mobile.getText().toString());
                params.put("password", pass.getText().toString());
                params.put("role", roleOut);
                params.put("username", username.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        roleOut = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
