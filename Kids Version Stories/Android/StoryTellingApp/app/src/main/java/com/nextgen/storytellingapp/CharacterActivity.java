package com.nextgen.storytellingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.storytellingapp.Adapter.CharacterAdapter;
import com.nextgen.storytellingapp.ModelClass.CharacterModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextWatcher;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

    private static String TAG ="CharacterActivity";

    RecyclerView charactersRV;
    ArrayList<CharacterModelClass> list;

    private GlobalPreference globalPreference;
    private String ip;

    EditText characterET;
    TextView noCharacterTV;
    Button continueBT;
    ImageView logoutIV;
    Boolean characterSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        characterSelected = globalPreference.getCharacterSelected();

        characterET = findViewById(R.id.characterTypeEditText);
        continueBT = findViewById(R.id.characterContinueButton);
        logoutIV = findViewById(R.id.logoutImageView);
        noCharacterTV = findViewById(R.id.noCharactersTextView);

        charactersRV = findViewById(R.id.charactersRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        charactersRV.setLayoutManager(layoutManager);



        getCharacters();

        characterET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    filterCharacters(query);
                } else {

                    resetCharacters();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        continueBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                characterSelected = globalPreference.getCharacterSelected();

                if (characterSelected){

                    Intent intent = new Intent(CharacterActivity.this, SoundActivity.class);
                    startActivity(intent);

                }else{

                    Toast.makeText(CharacterActivity.this, "Please Select Character", Toast.LENGTH_SHORT).show();
                }



            }
        });



        logoutIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();
            }
        });
    }

    private void getCharacters() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/kids_stories/api/getCharacters.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){
                    Toast.makeText(CharacterActivity.this, "No Characters Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String image = object.getString("image");

                            list.add(new CharacterModelClass(id,name,image));

                        }

                        CharacterAdapter adapter = new CharacterAdapter(list,CharacterActivity.this);
                        charactersRV.setAdapter(adapter);

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
        RequestQueue requestQueue = Volley.newRequestQueue(CharacterActivity.this);
        requestQueue.add(stringRequest);

    }

    private void filterCharacters(String query) {
        ArrayList<CharacterModelClass> filteredList = new ArrayList<>();

        for (CharacterModelClass character : list) {
            if (character.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(character);
            }
        }

        if (filteredList.isEmpty()) {
            noCharacterTV.setVisibility(View.VISIBLE);
            charactersRV.setVisibility(View.GONE);
        } else {
            noCharacterTV.setVisibility(View.GONE);
            charactersRV.setVisibility(View.VISIBLE);
            CharacterAdapter adapter = new CharacterAdapter(filteredList, CharacterActivity.this);
            charactersRV.setAdapter(adapter);
        }
    }

    private void resetCharacters() {
        if (list.isEmpty()) {
            noCharacterTV.setVisibility(View.VISIBLE);
            charactersRV.setVisibility(View.GONE);
        } else {
            noCharacterTV.setVisibility(View.GONE);
            charactersRV.setVisibility(View.VISIBLE);
            CharacterAdapter adapter = new CharacterAdapter(list, CharacterActivity.this);
            charactersRV.setAdapter(adapter);
        }
    }

    private void logout() {

        new AlertDialog.Builder(CharacterActivity.this)
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CharacterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        logout();
    }
}