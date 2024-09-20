package com.nextgen.storytellingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.storytellingapp.Adapter.StoryAdapter;
import com.nextgen.storytellingapp.ModelClass.StoryModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoryListActivity extends AppCompatActivity {


    private static String TAG ="NotesActivity";

    RecyclerView storyRV;
    ArrayList<StoryModelClass> list;
    private TextView appbarTV;
    private ImageView backIV;
    LinearLayout noStoriesLL;
    String plot,ip;
    GlobalPreference globalPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        plot = globalPreference.getPlot();

       // Toast.makeText(this, ""+plot, Toast.LENGTH_SHORT).show();

        appbarTV = findViewById(R.id.appBarTitle);
        backIV = findViewById(R.id.BackImageButton);

        storyRV = findViewById(R.id.storiesRecyclerView);
        noStoriesLL = findViewById(R.id.noStoriesLL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        storyRV.setLayoutManager(layoutManager);

        appbarTV.setText(plot);
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bIntent = new Intent(StoryListActivity.this,PlotActivity.class);
                startActivity(bIntent);
                finish();
            }
        });

        getStories();
    }

    private void getStories() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/kids_stories/api/getStories.php?plot="+plot, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){

                    storyRV.setVisibility(View.GONE);
                    noStoriesLL.setVisibility(View.VISIBLE);

                }
                else{
                    storyRV.setVisibility(View.VISIBLE);
                    noStoriesLL.setVisibility(View.GONE);
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String title = object.getString("title");
                            String desc = object.getString("story_desc");
                            String image = object.getString("story_image");

                            list.add(new StoryModelClass(id,title,desc,image));

                        }

                        StoryAdapter adapter = new StoryAdapter(list,getApplicationContext());
                        storyRV.setAdapter(adapter);

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}