package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotelMangement extends AppCompatActivity {

    private Button AddBtn, ViewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_mangement);


        AddBtn = (Button) findViewById(R.id.button3);
        ViewBtn = (Button) findViewById(R.id.button4);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelMangement.this, HotelManagementForm.class);
                startActivity(intent);
            }
        });

        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelMangement.this, ViewHotelDetails.class);
                startActivity(intent);
            }
        });

    }
}
