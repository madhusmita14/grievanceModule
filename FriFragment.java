package com.madhusmita.final__ois;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
public class FriFragment extends Fragment {


    View v;
    List<ModelTimeTable> itemData=new ArrayList<>();
    ListView listView;
    TimeTableManager timeTableManager;
    ListViewAdapterTimeTable listViewAdapterTimeTable;
    LinearLayout linearLayout;
    public FriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_fri, container, false);
        listView=v.findViewById(R.id.friRecyclerView);
        itemData=new ArrayList<>();

        loadItemData();


        return v;
    }
    private void loadItemData()
    {
        timeTableManager =new TimeTableManager(getContext(), new CustomeListener() {
            @Override
            public void onVollyResponce(String responce) {
                //Toast.makeText(getContext(),"Res"+responce, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject object=new JSONObject(responce);
                    JSONArray jsonArray=object.getJSONArray("Details");

                    for(int i=4;i<jsonArray.length();i++)
                    {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = object1.getJSONArray("Fri");
                        for (int j =0; j < jsonArray1.length(); j++) {
                            JSONObject object2 = jsonArray1.getJSONObject(j);
                            ModelTimeTable modelTimeTable = new ModelTimeTable(object2.getString("Period"),
                                    object2.getString("Teacher"),
                                    object2.getString("Room No"),
                                    object2.getString("Timing"));

                            itemData.add(modelTimeTable);
                        }
                        listViewAdapterTimeTable = new ListViewAdapterTimeTable(itemData, getContext());
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
