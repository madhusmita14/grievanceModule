package com.madhusmita.final__ois;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.madhusmita.final__ois.Adapters.HorizontalAdapterTimeTable;
import com.madhusmita.final__ois.EntityClasses.ButtonModel;
import com.madhusmita.final__ois.ManagerClasses.CustomeListener;
import com.madhusmita.final__ois.ManagerClasses.TimeTableManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimeTable extends AppCompatActivity {

    ImageView notification_img_toolbar;
    List<ButtonModel> itemDay;
    RecyclerView recyclerView;
    TimeTableManager timeTableManager;
    HorizontalAdapterTimeTable horizontalAdapterTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        notification_img_toolbar=(ImageView)findViewById(R.id.notification_img_toolbar);

        /*recyclerView=findViewById(R.id.day_recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);

        itemDay= new ArrayList<>();*/
        //loadItemDay();

        FragmentManager manager1=getSupportFragmentManager();
        FragmentTransaction transaction1=manager1.beginTransaction();
        MonFragment monFragment=new MonFragment();
        transaction1.add(R.id.fragmentid,monFragment);
        transaction1.commit();

        /*View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;

                if (v == findViewById(R.id.mon)) {
                    fragment = new MonFragment();
                } else if (v == findViewById(R.id.tue)) {
                    fragment = new TueFragment();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentid, fragment);
                fragmentTransaction.commit();
            }
        };*/
    }

   /* private void loadItemDay() {
        timeTableManager =new TimeTableManager(this, new CustomeListener() {
            @Override
            public void onVollyResponce(String responce) {
                //Toast.makeText(MainActivity.this,"Res"+responce, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = new JSONObject(responce);

                    JSONArray heroArray = obj.getJSONArray("Details");

                    for (int i = 0; i < heroArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroArray.getJSONObject(i);

                        //creating a hero object and giving them the values from json object
                        ButtonModel b= new ButtonModel(heroObject.getString("Day"));
                        itemDay.add(b);
                    }
                    horizontalAdapterTimeTable =new HorizontalAdapterTimeTable(itemDay,getApplicationContext(),TimeTable.this);
                    recyclerView.setAdapter(horizontalAdapterTimeTable);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponce(VolleyError errorResponce) {
                Toast.makeText(getApplicationContext(),"Res"+errorResponce.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        timeTableManager.postRequest();
    }
*/
}
