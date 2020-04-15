package com.sliit.project_elephas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Sight_Seen_Basic_Rule2 extends AppCompatActivity {

    private Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight__seen__basic__rule2);

        but2=(Button)findViewById(R.id.button13);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

    }
    public void openActivity2(){
        Intent intent2 = new Intent(this,Sight_seen_rules.class);
        startActivity(intent2);
    }
}
