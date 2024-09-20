package com.nextgen.storytellingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class HearStoryActivity extends AppCompatActivity {

    ImageView characterIV;
    ImageView playIV;
    ImageView stopIV;
    LinearLayout backLL;
    String characterImage;
    TextToSpeech myTTS;
    private GlobalPreference globalPreference;
    String ip;
    String story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_story);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();

        characterImage = globalPreference.getCharacterImage();

        story = getIntent().getStringExtra("desc");

        characterIV = findViewById(R.id.hCharacterImageView);
        playIV = findViewById(R.id.playImageView);
        stopIV = findViewById(R.id.stopImageView);
        backLL = findViewById(R.id.hBackLinearLayout);

        if (!characterImage.equals("")) {
            Glide.with(getApplicationContext())
                    .load("http://" + ip + "/kids_stories/characters_tbl/uploads/" + characterImage)
                    .into(characterIV);
        }

        myTTS=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    myTTS.setLanguage(Locale.UK);
                }
            }
        });

        playIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playIV.setVisibility(View.GONE);
                stopIV.setVisibility(View.VISIBLE);

                myTTS.speak(story, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        stopIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopIV.setVisibility(View.GONE);
                playIV.setVisibility(View.VISIBLE);
                myTTS.stop();
            }
        });

        backLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bIntent = new Intent(HearStoryActivity.this,StoryListActivity.class);
                startActivity(bIntent);
                finish();
            }
        });
    }
}