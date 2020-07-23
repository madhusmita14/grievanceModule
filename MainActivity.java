package com.madhusmita.final__ois;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Dialog popUpWindow;
    Button imageViewFile,imageViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popUpWindow=new Dialog(this);

        CardView cardView=(CardView)findViewById(R.id.GrievenceCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                popUpWindow.setContentView(R.layout.popupwindow);

                imageViewFile=popUpWindow.findViewById(R.id.imageViewFile);
                imageViewFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),FileGrievanceCell.class);
                        startActivity(intent);
                    }
                });

                imageViewStatus=popUpWindow.findViewById(R.id.imageViewStatus);
                imageViewStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent inten=new Intent(getApplicationContext(),GrievanceStatus.class);
                        startActivity(inten);

                    }
                });
                popUpWindow.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popUpWindow.show();
                //Intent intent=new Intent(MainActivity.this,CardActivity.class);
                //startActivity(intent);
            }
        });
        CardView cardView1=(CardView)findViewById(R.id.TimeTableCard);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TimeTable.class);
                startActivity(intent);
            }
        });
    }
}
