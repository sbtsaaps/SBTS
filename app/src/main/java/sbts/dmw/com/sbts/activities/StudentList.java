package sbts.dmw.com.sbts.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sbts.dmw.com.sbts.MySingleton;
import sbts.dmw.com.sbts.R;
import sbts.dmw.com.sbts.adapter.RecyclerViewAdapter;
import sbts.dmw.com.sbts.model.Anime;

public class StudentList extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest arrayRequest;
    private List<Anime> animeList = new ArrayList<>();
    private RecyclerView SLrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        SLrecyclerView = findViewById(R.id.recyclerView);
        jsonCall();

    }

    public void jsonCall(){

        arrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for(int i=0; i<response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();
                        anime.setName(jsonObject.getString("name"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setImageURL(jsonObject.getString("img"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setCategories(jsonObject.getString("categorie"));
                        animeList.add(anime);
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(animeList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(arrayRequest);

    }

    private void setUpRecyclerView(List<Anime> animeList) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,animeList);
        SLrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SLrecyclerView.setAdapter(myAdapter);

    }
}
