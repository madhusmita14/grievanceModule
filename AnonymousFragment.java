package com.madhusmita.final__ois;


import android.app.Dialog;
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
public class AnonymousFragment extends Fragment {

    View v;
    ArrayList<GrievenceModel> itemData=new ArrayList<>();
    ListView listView;
    ListViewAdapterGrievence listViewAdapterGrievence;
    GrievenceFileManager grievenceFileManager;
    Dialog Mystatusdilog;
    Button trackStatus;
    private String URL_DATA="https://api.myjson.com/bins/1arr0e";


    public AnonymousFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_anonymous, container, false);
        listView=v.findViewById(R.id.anonymousRecyclerView);

        Mystatusdilog= new Dialog(getContext());

        getMyList();

        return v;
    }
    private void getMyList() {

        grievenceFileManager=new GrievenceFileManager(getContext(), new CustomeListener() {
            @Override
            public void onVollyResponce(String responce) {
                try {
                    JSONObject object=new JSONObject(responce);
                    JSONArray array=object.getJSONArray("Anonymous");
                    for(int i=1;i<array.length();i++)
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
                               /* Intent i=new Intent(getContext(),OnymousActivity.class);
                                startActivity(i);*/
                                Mystatusdilog.setContentView(R.layout.grievence_pop_up);

                                trackStatus= Mystatusdilog.findViewById(R.id.trackStatus);
                                trackStatus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Mystatusdilog.dismiss();
                                        //Toast.makeText(getApplicationContext(),"anonymous",Toast.LENGTH_SHORT).show();
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
