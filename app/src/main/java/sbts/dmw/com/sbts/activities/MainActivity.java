package sbts.dmw.com.sbts.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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

import sbts.dmw.com.sbts.MySingleton;
import sbts.dmw.com.sbts.R;

public class MainActivity extends AppCompatActivity {

    private int LOCATION_PERMISSION_CODE = 1;
    private EditText usernameEnMap;
    private EditText passwordEnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.registerUser);
        String text = "If your not a registered user click here to sign-up";
        SpannableString ss = new SpannableString(text);
        final Intent RegForm = new Intent(this,RegisterUser.class);
        final Intent PassReset = new Intent(this,PasswordReset.class);
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(RegForm);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ss.setSpan(cs,30,40,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView1 = findViewById(R.id.forgotPass);
        String text1 = "Forgot Password?";
        SpannableString ssf = new SpannableString(text1);
        ClickableSpan csf = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(PassReset);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ssf.setSpan(csf,0,16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(ssf);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return;
        }else{
            requestLocationPermission();
        }
    }

    public void EnableMaps(View view){
        usernameEnMap = findViewById(R.id.username);
        passwordEnMap = findViewById(R.id.password);

        if(usernameEnMap.getText().toString().isEmpty() || passwordEnMap.getText().toString().isEmpty()){
            Toast.makeText(this,"Please fill both the username and password fields.",Toast.LENGTH_LONG).show();
        }else{
            String url ="https://defcon12.000webhostapp.com/login.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.substring(1,2).contentEquals("0")){
                                Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_LONG).show();
                                if(response.substring(2,3).contentEquals("M")){
                                    Intent EnAct = new Intent(MainActivity.this,MapsActivity.class);
                                    startActivity(EnAct);
                                }else{
                                    Intent Attend = new Intent(getApplicationContext(),Attendee.class);
                                    startActivity(Attend);
                                }
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Please enter a valid username/password",Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Sorry, there was an error in completing your request.",Toast.LENGTH_LONG).show();
                        }
                    }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("password", passwordEnMap.getText().toString());
                        params.put("username", usernameEnMap.getText().toString());
                        return params;
                    }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
    }

    private void requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Allow SBTS to access this device's location?")
                    .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
