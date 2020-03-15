package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerReqGatheringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_req_gathering);
    }

    public void redirectToAfterLoginCustomerActivity(View view){
        //go back when click on cancel button
        Intent intent = new Intent(this,AfterLoginCustomerActivity.class);
        startActivity(intent);

    }
}
