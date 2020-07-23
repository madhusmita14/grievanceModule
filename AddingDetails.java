package com.madhusmita.final__ois;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.madhusmita.final__ois.FileGrievanceCell.arrayadapter;

public class AddingDetails extends AppCompatActivity {

    Button browse,save;
    TextView uploadedText;
    EditText documentTitle;
    Intent myFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_details);

        browse=findViewById(R.id.browse);
        save=findViewById(R.id.save);
        uploadedText=findViewById(R.id.uploadedText);
        documentTitle=findViewById(R.id.documentTitleText);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,11);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uploaded_file_text=uploadedText.getText().toString();
                String document_title_text=documentTitle.getText().toString();

                if(uploaded_file_text.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"File must be uploaded",Toast.LENGTH_SHORT).show();
                }
                else if (document_title_text.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Document title must be mentioned",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i=new Intent(getApplicationContext(),FileGrievanceCell.class);
                    i.putExtra("uploadedFile",uploaded_file_text);
                    i.putExtra("documentTitle",document_title_text);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 11:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();
                    uploadedText.setText(path);
                }
                break;
        }
    }
}
