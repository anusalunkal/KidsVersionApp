package com.nextgen.storytellingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class StoriesActivity extends AppCompatActivity {

    private static String TAG ="StoriesActivity";

    TextView titleTV;
    TextView descTV;
    ImageView storyIV;
    ImageView backIV;
    Button hearStoryBT;
    String storyId;
    TextToSpeech myTTS;
    ScrollView descScrollView;

    private GlobalPreference globalPreference;
    private String ip;
    String desc;
    String[] sentences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();

        storyId = getIntent().getStringExtra("storyId");

        titleTV = findViewById(R.id.sTitleTextView);
        descTV = findViewById(R.id.sDescTextView);
        storyIV = findViewById(R.id.sStoryImageView);
        backIV = findViewById(R.id.sBackImageView);
        hearStoryBT = findViewById(R.id.hearStoryButton);
        descScrollView = findViewById(R.id.descScrollView);

        //Toast.makeText(this, ""+storyId, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate: "+"inside fnnn");
        
        loadStories();

        myTTS=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    myTTS.setLanguage(Locale.UK);
                }
            }
        });

        hearStoryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StoriesActivity.this,HearStoryActivity.class);
                intent.putExtra("desc",desc);
                startActivity(intent);
            }
        });

        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bIntent = new Intent(StoriesActivity.this,StoryListActivity.class);
                startActivity(bIntent);
                finish();
            }
        });


    }





    private void loadStories() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ip+"/kids_stories/api/storyDetails.php?storyId="+storyId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if(response.equals("failed")){
                    Toast.makeText(StoriesActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String title = object.getString("title");
                            desc = object.getString("story_desc");
                            String image = object.getString("story_image");

                            if (!image.equals("")) {
                                Glide.with(getApplicationContext())
                                        .load("http://" + ip + "/kids_stories/story_tbl/uploads/" + image)
                                        .into(storyIV);
                            }

                            titleTV.setText(title);
                            descTV.setText(desc);



                        }

                    } catch (JSONException e) {
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