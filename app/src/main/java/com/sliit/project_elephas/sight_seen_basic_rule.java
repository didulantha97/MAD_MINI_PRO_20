package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sight_seen_basic_rule extends AppCompatActivity {
    private Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_seen_basic_rule);

        but1=(Button)findViewById(R.id.dubut11);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
    }
    public void openActivity1(){
        Intent intent1 = new Intent(this,sight_seen_basic_rule2.class);
        startActivity(intent1);
    }
}
