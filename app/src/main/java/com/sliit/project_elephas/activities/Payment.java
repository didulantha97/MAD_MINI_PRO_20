package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;

import com.sliit.project_elephas.R;

public class Payment extends AppCompatActivity {

    Button pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //Define components
        pay = (Button) findViewById(R.id.save);

        //set button colors
        pay.setBackgroundColor(Color.parseColor("#03A9F4"));





    }
}
