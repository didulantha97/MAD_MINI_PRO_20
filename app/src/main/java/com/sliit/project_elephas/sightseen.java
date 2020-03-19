package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sightseen extends AppCompatActivity {
    private Button but1;
    private Button but2;
    private Button but3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightseen);

        but1=(Button) findViewById(R.id.dubut3);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
        but2=(Button)findViewById(R.id.dubut4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        but3=(Button) findViewById(R.id.dubut5);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }

    public void openActivity1(){
        Intent intent1 = new Intent(this,add_new_sight.class);
        startActivity(intent1);
    }

    public void openActivity2(){
        Intent intent2 = new Intent(this,sight_seen_details_page.class);
        startActivity(intent2);
    }
    public void openActivity3(){
        Intent intent3 = new Intent(this,sight_seen_basic_rule.class);
        startActivity(intent3);
    }



}
