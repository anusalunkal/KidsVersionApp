package com.nextgen.storytellingapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class GlobalPreference {

    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    private Context context;
    public GlobalPreference(Context context) {
        sharedPreference = context.getSharedPreferences("sample",MODE_PRIVATE);
        editor = sharedPreference.edit();
    }
    public void saveIP(String ipaddress){
        editor.putString("ip",ipaddress);
        editor.apply();
    }
    public String getIP(){
        return  sharedPreference.getString("ip","");
    }

    public void saveID(String uid){
        editor.putString("user_id",uid);
        editor.apply();
    }
    public String getID(){
        return  sharedPreference.getString("user_id","");
    }

    public void saveName(String name){
        editor.putString("name",name);
        editor.apply();
    }
    public String getName(){
        return  sharedPreference.getString("name","");
    }

    public void saveCharacter(String character){
        editor.putString("character",character);
        editor.apply();
    }
    public String getCharacter(){
        return  sharedPreference.getString("character","");
    }

    public void saveCharacterImage(String characterImage){
        editor.putString("characterImage",characterImage);
        editor.apply();
    }
    public String getCharacterImage(){
        return  sharedPreference.getString("characterImage","");
    }

    public void saveVoice(String voice){
        editor.putString("voice",voice);
        editor.apply();
    }
    public String getVoice(){
        return  sharedPreference.getString("voice","");
    }

    public void savePlot(String plot){
        editor.putString("plot",plot);
        editor.apply();
    }
    public String getPlot(){
        return  sharedPreference.getString("plot","");
    }

    public void saveCharacterSelected(Boolean characterSelected){
        editor.putBoolean("characterSelected",characterSelected);
        editor.apply();
    }
    public Boolean getCharacterSelected(){
        return  sharedPreference.getBoolean("characterSelected", Boolean.parseBoolean(""));
    }

    public void savePlotSelected(Boolean plotSelected){
        editor.putBoolean("plotSelected",plotSelected);
        editor.apply();
    }
    public Boolean getPlotSelected(){
        return  sharedPreference.getBoolean("plotSelected", Boolean.parseBoolean(""));
    }


}
