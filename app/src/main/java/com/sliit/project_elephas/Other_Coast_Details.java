package com.sliit.project_elephas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Other_Coast_Details extends AppCompatActivity {
   private Button but1;
   private Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other__coast__details);

        but1=(Button)findViewById(R.id.button9);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        but2=(Button)findViewById(R.id.button15);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity1(){
        Intent intent1 = new Intent(this,other_packages.class);
        startActivity(intent1);
    }


    public void openActivity2(){
        Intent intent2 = new Intent(this,Add_New_Cost.class);
        startActivity(intent2);
    }


}