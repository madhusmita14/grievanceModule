package com.madhusmita.final__ois;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.madhusmita.final__ois.Adapters.ListViewAdapterGrievence;
import com.madhusmita.final__ois.EntityClasses.GrievenceModel;
import com.madhusmita.final__ois.ManagerClasses.CustomeListener;
import com.madhusmita.final__ois.ManagerClasses.GrievenceFileManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnymousFragment extends Fragment {

    View v;
    ArrayList<GrievenceModel> itemData=new ArrayList<>();
    ListView listView;
    ListViewAdapterGrievence listViewAdapterGrievence;
    GrievenceFileManager grievenceFileManager;
    Dialog Mystatusdilog;
    Button trackStatus;
    //https://api.myjson.com/bins/1arr0e

    public OnymousFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_onymous, container, false);
        listView=v.findViewById(R.id.onymousRecyclerView);

        Mystatusdilog= new Dialog(getContext());

         getMyList();
            
         return v;
    }

    private void getMyList() {

        grievenceFileManager=new GrievenceFileManager(getContext(), new CustomeListener() {
            @Override
            public void onVollyResponce(String responce) {

                try {
                    GrievanceStatus.progressBar.setVisibility(View.INVISIBLE);
                    //GrievanceStatus.progressDialog.dismiss();
                    JSONObject object=new JSONObject(responce);
                    JSONArray array=object.getJSONArray("Onymous");

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object1=array.getJSONObject(i);
                        GrievenceModel model=new GrievenceModel(object1.getString("Id"));

                        itemData.add(model);
                    }
                    listViewAdapterGrievence =new ListViewAdapterGrievence(itemData,getContext());
                    listView.setAdapter(listViewAdapterGrievence);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getContext(),"card"+position,Toast.LENGTH_SHORT).show();

                                Mystatusdilog.setContentView(R.layout.grievence_pop_up);

                                trackStatus= Mystatusdilog.findViewById(R.id.trackStatus);
                                trackStatus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Mystatusdilog.dismiss();
                                        Toast.makeText(getContext(),"tracking.......",Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(getContext(),TrackingDetails.class);
                                        startActivity(i);
                                    }
                                });
                                Mystatusdilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                                Mystatusdilog.show();
                            }
                        });
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponce(VolleyError errorResponce) {
                Toast.makeText(getContext(),"Res"+errorResponce.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        grievenceFileManager.postRequest();
    }

}
