package com.madhusmita.final__ois;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.madhusmita.final__ois.EntityClasses.SpinnerModel;
import com.madhusmita.final__ois.ManagerClasses.CustomeListener;
import com.madhusmita.final__ois.ManagerClasses.SpinnerManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FileGrievanceCell extends AppCompatActivity {

    EditText grv_desc,others;
    CheckBox checkBox;
    Button btn_cancel,btnFile;

    ImageButton addButton;
    public static ArrayList arrayList=new ArrayList<String>();
    public static ArrayAdapter arrayadapter;
    public static String GetItem,GetTitle;

    private ArrayList<SpinnerModel> goodSpinnerModelArrayList;
    private ArrayList<String> categorynames = new ArrayList<String>();
    private ArrayList<String> categoryId = new ArrayList<String>();
    SpinnerManager spinnerManager;

    private Spinner spinner;
    SpinnerModel playerSpinnerModel;

    String grv_id,grv_cat,selected,temp;
    String str,other;
    public static Boolean check1;
    GridView gridview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_grievance_cell);

        spinner=(Spinner)findViewById(R.id.country_Name);

        grv_desc=(EditText) findViewById(R.id.editText);
        others=(EditText)findViewById(R.id.other_editText);

        addButton=findViewById(R.id.mulitplefile);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddingDetails.class);
                startActivity(i);
            }
        });
        final Intent i=getIntent();
        GetItem=getIntent().getStringExtra("uploadedFile");
        GetTitle=getIntent().getStringExtra("documentTitle");
        arrayList.add(arrayList.size(),GetItem);
        arrayList.add(arrayList.size(),GetTitle);
        Toast.makeText(getApplicationContext(),""+arrayList, Toast.LENGTH_SHORT).show();

        gridview = (GridView) findViewById(R.id.gridView1);

        arrayadapter = new ArrayAdapter<String>(FileGrievanceCell.this,android.R.layout.simple_list_item_1, arrayList);
        gridview.setAdapter(arrayadapter);

        checkBox=(CheckBox)findViewById(R.id.checkBox);

        loadSpinnerData();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        grv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grv_desc.setCursorVisible(true);
            }
        });

        btnFile=(Button)findViewById(R.id.filegrievancebutton);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str =grv_desc.getText().toString();
                other=others.getText().toString();

                Intent intent=new Intent(getApplicationContext(),AcknowledgePage.class);

                intent.putExtra("getData",selected);
                intent.putExtra("grv",str);
                intent.putExtra("checkbox",checkBox.isChecked());
                //intent.putExtra("multipleItem",arrayList);
                intent.putExtra("other",other);

                startActivity(intent);
            }
        });
        btn_cancel=(Button)findViewById(R.id.cancelbutton);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void onCheckBox(View view) {
        boolean checked=((CheckBox)view).isChecked();

        switch (view.getId())
        {
            case R.id.checkBox:
                if(checked)
                {
                    Intent i=new Intent();
                    i.putExtra("anonymous","anonymous");
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent();
                    i.putExtra("onymous","onymous");
                    startActivity(i);
                }
                break;
        }
    }
    private void loadSpinnerData() {
       /* strJSON="{\"Category\":" +"\""+temp+"\",+
                " \"Grievance\":"+"\""+str+"\"",+
                "{\"DocDetail\":"[\"{\"\"UploadDoc\":\""+GetItem+"\",\"DocTitle\":"+"\""+GetTitle+"\"}]}",+
                " \"Anonymous\":"+"\""+check1+"\"}";
        */
        //strJSON="{\"DocDetail\":[\"{\"\"UploadDoc\":\""+GetItem+"\",\"DocTitle\":"+"\""+GetTitle+"\"}]}";
        //Toast.makeText(getApplicationContext(),strJSON,Toast.LENGTH_SHORT).show();

        spinnerManager = new SpinnerManager(getApplicationContext(), new CustomeListener() {
            @Override
            public void onVollyResponce(String responce) {

                try
                {
                    JSONObject obj = new JSONObject(responce);
                    if (obj.optString("Success").equals("1")) {

                        goodSpinnerModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("Data");

                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject dataobj = dataArray.getJSONObject(i);

                            grv_id=dataobj.getString("GrievanceCatID");
                            grv_cat=dataobj.getString("GrievanceCat");
                            playerSpinnerModel = new SpinnerModel(grv_id,grv_cat);
                            playerSpinnerModel.setGrv_id(dataobj.getString("GrievanceCatID"));
                            goodSpinnerModelArrayList.add(playerSpinnerModel);
                        }

                        for (int i = 0; i < goodSpinnerModelArrayList.size(); i++) {
                            categorynames.add(goodSpinnerModelArrayList.get(i).getCategoryName().toString());
                        }
                        for (int i = 0; i < goodSpinnerModelArrayList.size(); i++) {
                            categoryId.add(goodSpinnerModelArrayList.get(i).getGrv_id().toString().trim());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,categorynames);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(spinnerArrayAdapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                selected=spinner.getItemAtPosition(position).toString();
                                temp=categoryId.get(position);
                                //Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT).show();

                                if(selected.equals("Others"))
                                {
                                    others.setVisibility(View.VISIBLE);
                                }
                                else
                                {
                                    others.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponce(VolleyError errorResponce) {
                Toast.makeText(getApplicationContext(), "Res" + errorResponce.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        spinnerManager.postRequest();
    }
}
