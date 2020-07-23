package com.madhusmita.final__ois;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import static com.madhusmita.final__ois.FileGrievanceCell.check1;

public class AcknowledgePage extends AppCompatActivity {

    TextView getGrievenceId;
    TextView getPassword;
    TextView getComplainant;
    TextView getCategory;
    TextView getGrievence;

    TableLayout tableLayout;
    TableRow passwordRow;

    /*public static ArrayList<String> multipleItem=new ArrayList<String>();
    ArrayAdapter<String>multipleItemAdapter;
    GridView multipleItemList;
    String t;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_grievance_button);

        getGrievenceId=findViewById(R.id.getGrievenceId);
        getPassword=findViewById(R.id.getPassword);
        getComplainant=findViewById(R.id.getComplaint);
        getCategory=findViewById(R.id.getCategory);
        getGrievence=findViewById(R.id.getGrievence);

        //multipleItemList=findViewById(R.id.multiple_list_view);

        tableLayout=findViewById(R.id.tlTable01);
        passwordRow=findViewById(R.id.passwordRow);

        Intent intent = getIntent();
        String valueSpinnerId= getIntent().getStringExtra("getData");
        //String valueOther=getIntent().getStringExtra("other");

        /*if(valueSpinnerId.equals("7"))
        {
            getCategory.setText(valueOther);
        }
        else
        {
            getCategory.setText(valueSpinnerId);
        }*/
        getCategory.setText(valueSpinnerId);
        String str = intent.getStringExtra("grv");
        getGrievence.setText(str);

        check1 =intent.getExtras().getBoolean("checkbox");
        if(check1)
        {
            getComplainant.setText("Anonymous");

        }
        else
        {
            getComplainant.setText("Onymous");
        }
        if(check1.booleanValue()==true)
        {
            passwordRow.setVisibility(VISIBLE);
        }
        else
        {
            passwordRow.setVisibility(INVISIBLE);
        }
       /* getIntentItem=getIntent().getStringExtra("multipleItem");
        getIntentTitle=getIntent().getStringExtra("multipleTitle");
        multipleItem.add(multipleItem.size(),getIntentItem);
        multipleItem.add(multipleItem.size(),getIntentTitle);*/

        //multipleItem = (ArrayList<String>) getIntent().getSerializableExtra("multipleItem");
        //multipleItemAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,multipleItem);
        //multipleItemList.setAdapter(multipleItemAdapter);
        //Toast.makeText(getApplicationContext(),""+ multipleItem,Toast.LENGTH_SHORT).show();

        Button btn=(Button)findViewById(R.id.confirmbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AcknowledgePage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
