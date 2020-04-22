package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.hasinthi.HotelMangement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageInformationForToursActivity extends AppCompatActivity {

    // This is used for the intent, we get the admin id and some info using these
//    private static String ADMIN_NAME = null;
//    //private static String ADMIN_ID = null;
//    private static String ADMIN_PASSPORT_NO = null;
//    private static String ADMIN_EMAIL = null;

    Button btnHotelManagement;
    Button btnSightSeeingPart;
    Button btnAdditionalCostPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_information_for_tours);

        // Get the values passed through intent and put those values into a String variable
//        ADMIN_NAME = getIntent().getExtras().getString("ADMIN_NAME");
//        //ADMIN_ID = getIntent().getExtras().getString("ADMIN_ID");
//        ADMIN_PASSPORT_NO = getIntent().getExtras().getString("ADMIN_PASSPORT_NO");
//        ADMIN_EMAIL = getIntent().getExtras().getString("ADMIN_EMAIL");

        btnHotelManagement = (Button)findViewById(R.id.button11);
        btnSightSeeingPart = (Button)findViewById(R.id.button12);
        btnAdditionalCostPart = (Button)findViewById(R.id.button13);


        // redirect to Hashinthi's part -  Additional Cost
        btnHotelManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HotelMangement.class);
                //intent.putExtra("ADMIN_ID",ADMIN_ID);
//                intent.putExtra("ADMIN_PASSPORT_NO",ADMIN_PASSPORT_NO);
//                intent.putExtra("ADMIN_EMAIL",ADMIN_EMAIL);
//                intent.putExtra("ADMIN_NAME",ADMIN_NAME);
                startActivity(intent);
            }
        });

        // redirect to Dulangi's part -  Sight Seeing Cost
        btnSightSeeingPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main_Activity_Dula.class);
                //intent.putExtra("ADMIN_ID",ADMIN_ID);
//                intent.putExtra("ADMIN_PASSPORT_NO",ADMIN_PASSPORT_NO);
//                intent.putExtra("ADMIN_EMAIL",ADMIN_EMAIL);
//                intent.putExtra("ADMIN_NAME",ADMIN_NAME);
                startActivity(intent);

            }
        });



        // redirect to Dulangi's part -  Additional Cost
        btnAdditionalCostPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main_Activity_Dula.class);
//                //intent.putExtra("ADMIN_ID",ADMIN_ID);
//                intent.putExtra("ADMIN_PASSPORT_NO",ADMIN_PASSPORT_NO);
//                intent.putExtra("ADMIN_EMAIL",ADMIN_EMAIL);
//                intent.putExtra("ADMIN_NAME",ADMIN_NAME);
                startActivity(intent);

            }
        });
    }
}