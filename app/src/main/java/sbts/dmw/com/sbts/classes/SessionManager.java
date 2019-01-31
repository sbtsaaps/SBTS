package sbts.dmw.com.sbts.classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import sbts.dmw.com.sbts.activities.Attendee;
import sbts.dmw.com.sbts.activities.MainActivity;
import sbts.dmw.com.sbts.activities.MapsActivity;
import sbts.dmw.com.sbts.activities.SessionCheck;
import sbts.dmw.com.sbts.activities.main_container;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String EMAIL = "EMAIL";
    public static final String ROLE = "ROLE";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String email, String role){
        editor.putBoolean(LOGIN, true);
        editor.putString(EMAIL, email);
        editor.putString(ROLE, role);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogIn(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
            ((SessionCheck) context).finish();
        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        user.put(ROLE, sharedPreferences.getString(ROLE,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
        try{
            ((main_container) context).finish();
        }catch (Exception ignored){

        }
        try {
            ((MapsActivity) context).finish();
        }catch (Exception ignored){

        }
    }
}
