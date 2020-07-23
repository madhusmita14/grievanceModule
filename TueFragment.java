package com.madhusmita.final__ois;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.madhusmita.final__ois.Adapters.ListViewAdapterTimeTable;
import com.madhusmita.final__ois.EntityClasses.ModelTimeTable;
import com.madhusmita.final__ois.ManagerClasses.CustomeListener;
import com.madhusmita.final__ois.ManagerClasses.TimeTableManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TueFragment extends Fragment {

    View v;
    List<ModelTimeTable> itemData=new ArrayList<>();
    ListView listView;
    TimeTableManager timeTableManager;
    ListViewAdapterTimeTable listViewAdapterTimeTable;


    public TueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragm
        v=inflater.inflate(R.layout.fragment_tue,container,false);
        listView=v.findViewById(R.id.tueRecyclerView);

        loadItemDataTue();

        return v;
    }
    private void loadItemDataTue() {

        timeTableManager =new TimeTableManager(getContext(), new CustomeListener() {
            @Override
            public void onVollyResponce(String responce) {
                try {
                    JSONObject objectTue=new JSONObject(responce);
                    JSONArray jsonArrayTue=objectTue.getJSONArray("Details");
                    for(int i=1;i<jsonArrayTue.length();i++)
                    {
                        JSONObject objectTue1=jsonArrayTue.getJSONObject(i);
                        JSONArray jsonArrayTue1=objectTue1.getJSONArray("Tue");
                        for(int j=0;j<jsonArrayTue1.length();j++)
                        {
                            JSONObject jsonObjectTue2=jsonArrayTue1.getJSONObject(j);
                            ModelTimeTable modelTimeTable =new ModelTimeTable(jsonObjectTue2.getString("Period"),
                                                   jsonObjectTue2.getString("Teacher"),
                                                   jsonObjectTue2.getString("Room No"),
                                                    jsonObjectTue2.getString("Timing"));
                            itemData.add(modelTimeTable);
                        }
                        listViewAdapterTimeTable =new ListViewAdapterTimeTable(itemData,getContext());
                        listView.setAdapter(listViewAdapterTimeTable);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponce(VolleyError errorResponce) {
                Toast.makeText(getContext(),"Res"+errorResponce.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        timeTableManager.postRequest();
    }
}
