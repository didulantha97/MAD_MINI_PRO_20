package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageInformationForToursActivity extends AppCompatActivity {
    private Button but1;
    private Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_information_for_tours);

        but1=(Button)findViewById(R.id.button12);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();

            }
        });

        but2 = (Button)findViewById(R.id.dulbut13);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity1(){
        Intent intent1 = new Intent(this,Main_Activity_Dula.class);
        startActivity(intent1);

    }

    public void openActivity2(){
        Intent intent2 = new Intent(this,Other_Coast_Details.class);
        startActivity(intent2);
    }
}
