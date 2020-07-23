package com.madhusmita.final__ois;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class GrievanceStatus extends AppCompatActivity {
RadioButton anonymousbtn,onymousbtn;
RadioGroup choosebtn;
Dialog Mystatusdilog;
Button okbtn;
public  static ProgressDialog progressDialog;
public static ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_status);

        Mystatusdilog = new Dialog(this);

        onymousbtn=findViewById(R.id.radioButton1);
        anonymousbtn=findViewById(R.id.radioButton2);


        choosebtn=findViewById(R.id.choosebtn);
        choosebtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        Toast.makeText(getApplicationContext(), "onymous", Toast.LENGTH_SHORT).show();

                        progressBar = (ProgressBar) findViewById(R.id.progressBarFragment);

                        progressBar.setVisibility(View.VISIBLE);

                        OnymousFragment onymousFragment = new OnymousFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameId,onymousFragment);
                        transaction.commit();
                        break;
                    case R.id.radioButton2:
                        Mystatusdilog.setContentView(R.layout.password_pop);

                        okbtn= Mystatusdilog.findViewById(R.id.okbtn);
                        okbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Mystatusdilog.dismiss();
                                //Toast.makeText(getApplicationContext(),"anonymous",Toast.LENGTH_SHORT).show();
                                AnonymousFragment anonymousFragment = new AnonymousFragment();
                                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                                transaction1.replace(R.id.frameId, anonymousFragment);
                                transaction1.commit();
                            }
                        });
                        Mystatusdilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        Mystatusdilog.show();
                        break;
                }
            }
        });
    }
}
