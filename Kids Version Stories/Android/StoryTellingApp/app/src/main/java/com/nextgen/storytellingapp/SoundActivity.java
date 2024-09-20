package com.nextgen.storytellingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class SoundActivity extends AppCompatActivity {

    TextView characterTV;
    ImageView characterIV;
    RadioGroup voiceRG;
    RadioButton manRB,ladiesRB,kidsRB;
    Button continueBT;
    String characterName,characterImage;
    String selectedVoice= "";

    private GlobalPreference globalPreference;
    private String ip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();

        characterName = globalPreference.getCharacter();
        characterImage = globalPreference.getCharacterImage();

        characterTV = findViewById(R.id.sCharacterNameTextView);
        characterIV = findViewById(R.id.sCharacterIconImageView);
        voiceRG = findViewById(R.id.voiceRadioGroup);
        ladiesRB = findViewById(R.id.ladiesRadioButton);
        manRB = findViewById(R.id.manRadioButton);
        kidsRB = findViewById(R.id.kidsRadioButton);
        continueBT = findViewById(R.id.continueButton);

        manRB.setEnabled(false);
        kidsRB.setEnabled(false);

        characterTV.setText(characterName);

        if (!characterImage.equals("")) {
            Glide.with(getApplicationContext())
                    .load("http://" + ip + "/kids_stories/characters_tbl/uploads/" + characterImage)
                    .into(characterIV);
        }

        voiceRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                RadioButton radioButton = findViewById(checkedId);
                selectedVoice = radioButton.getText().toString();

                //Toast.makeText(SoundActivity.this, "Voice"+selectedVoice, Toast.LENGTH_SHORT).show();

            }
        });

        continueBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedVoice.matches("")){
                    Toast.makeText(SoundActivity.this, "Please Select voice", Toast.LENGTH_SHORT).show();
                }else {


                    globalPreference.saveVoice(selectedVoice);

                    Intent intent = new Intent(SoundActivity.this,PlotActivity.class);
                    startActivity(intent);


                }
            }
        });






    }
}