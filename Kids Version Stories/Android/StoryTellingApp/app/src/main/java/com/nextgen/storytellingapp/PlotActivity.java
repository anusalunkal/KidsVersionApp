package com.nextgen.storytellingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.storytellingapp.Adapter.PlotAdapter;
import com.nextgen.storytellingapp.ModelClass.PlotModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlotActivity extends AppCompatActivity {

    private static String TAG ="PlotActivity";

    RecyclerView plotRV;
    ArrayList<PlotModelClass> list;

    private GlobalPreference globalPreference;
    private String ip;

    EditText plotET;
    TextView noPlotTV;
    Button continueBT;
    Boolean plotSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        plotSelected = globalPreference.getPlotSelected();

        plotET = findViewById(R.id.plotSearchEditText);
        continueBT = findViewById(R.id.plotContinueButton);

        plotRV = findViewById(R.id.plotRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        plotRV.setLayoutManager(gridLayoutManager);

        noPlotTV = findViewById(R.id.noPlotTextView);

        getStoryPlots();

        plotET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    searchPlots(query);
                } else {

                    resetPlots();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        continueBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                plotSelected = globalPreference.getPlotSelected();

                if (plotSelected){

                  //  Toast.makeText(PlotActivity.this, " Selected", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PlotActivity.this, StoryListActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(PlotActivity.this, "Please Select Plot", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private void getStoryPlots() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/kids_stories/api/getPlots.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){
                    Toast.makeText(PlotActivity.this, "No Characters Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String plot = object.getString("plot");

                            list.add(new PlotModelClass(id,plot));

                        }

                        PlotAdapter adapter = new PlotAdapter(list,PlotActivity.this);
                        plotRV.setAdapter(adapter);

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
        RequestQueue requestQueue = Volley.newRequestQueue(PlotActivity.this);
        requestQueue.add(stringRequest);

    }

    private void searchPlots(String query) {
        ArrayList<PlotModelClass> filteredList = new ArrayList<>();

        for (PlotModelClass plots : list) {
            if (plots.getPlot().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(plots);
            }
        }

        if (filteredList.isEmpty()) {
            noPlotTV.setVisibility(View.VISIBLE);
            plotRV.setVisibility(View.GONE);
        } else {
            noPlotTV.setVisibility(View.GONE);
            plotRV.setVisibility(View.VISIBLE);
            PlotAdapter adapter = new PlotAdapter(filteredList, PlotActivity.this);
            plotRV.setAdapter(adapter);
        }
    }

    private void resetPlots() {
        if (list.isEmpty()) {
            noPlotTV.setVisibility(View.VISIBLE);
            plotRV.setVisibility(View.GONE);
        } else {
            noPlotTV.setVisibility(View.GONE);
            plotRV.setVisibility(View.VISIBLE);
            PlotAdapter adapter = new PlotAdapter(list, PlotActivity.this);
            plotRV.setAdapter(adapter);
        }
    }
}