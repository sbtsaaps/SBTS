package sbts.dmw.com.sbts.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sbts.dmw.com.sbts.adapter.RecyclerViewAdapter;
import sbts.dmw.com.sbts.classes.MySingleton;
import sbts.dmw.com.sbts.R;
import sbts.dmw.com.sbts.model.Student;

public class StudentList extends AppCompatActivity {

    private final String JSON_URL = "https://defcon12.000webhostapp.com/Recycle.php";
    private JsonArrayRequest request;
    private List<Student> studentList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        studentList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_id);

        jsonRequest();
    }

    private void jsonRequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for(int i=response.length()-1;i>=0;i--){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Student s = new Student();
                        s.setName(jsonObject.getString("Name"));
                        s.setDivision(jsonObject.getString("Division"));
                        s.setRoll_no(jsonObject.getString("Rollno"));
                        s.setS_class(jsonObject.getString("Class"));
                        s.setPhoto(jsonObject.getString("Photo"));
                        studentList.add(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setupRecyclerView(studentList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    private void setupRecyclerView(List<Student> studentList) {

        RecyclerViewAdapter sAdapter = new RecyclerViewAdapter(this,studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sAdapter);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
