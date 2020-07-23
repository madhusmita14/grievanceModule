package com.madhusmita.final__ois;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.madhusmita.final__ois.Adapters.ListViewAdapter_Tracking;
import com.madhusmita.final__ois.EntityClasses.TrackingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrackingDetails extends AppCompatActivity {

    ImageView arrow_img_toolbar;
    TextView time,status;
    ListView listView;
    List<TrackingModel> heroList;
    private static final String JSON_URL = "http://www.json-generator.com/api/json/get/ceUrznKWIy?indent=2";//"https://api.myjson.com/bins/1fh1b4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_details);

        arrow_img_toolbar=(ImageView)findViewById(R.id.arrow_img_toolbar);
        time=(TextView)findViewById(R.id.textViewTime);
        status=(TextView)findViewById(R.id.textViewStatus);

        listView = (ListView) findViewById(R.id.list_of_tracking);
        heroList = new ArrayList<>();

        loadHeroList();
    }
    private void loadHeroList() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray heroArray = obj.getJSONArray("Track");

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                String status_of_track=heroObject.getString("status");
                                String time_of_track=heroObject.getString("time");

                                TrackingModel hero=new TrackingModel(status_of_track,time_of_track);
                                heroList.add(hero);
                            }

                            ListViewAdapter_Tracking adapter = new ListViewAdapter_Tracking(heroList, getApplicationContext());

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }
}
