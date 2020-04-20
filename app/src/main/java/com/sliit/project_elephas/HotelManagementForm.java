package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HotelManagementForm extends AppCompatActivity {

    private Button SubBtn, CanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_management_form);

        SubBtn = (Button) findViewById(R.id.button);
        CanBtn = (Button) findViewById(R.id.button2);

        SubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelManagementForm.this, ViewHotelDetails.class);
                startActivity(intent);
            }
        });

        CanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelManagementForm.this, HotelMangement.class);
                startActivity(intent);
            }
        });

     }
    }
