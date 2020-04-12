package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sliit.project_elephas.activities.all_customer_payments;

public class AfterLoginCustomerActivity extends AppCompatActivity {

    Button payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_customer);

        payment = (Button) findViewById(R.id.payment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginCustomerActivity.this, all_customer_payments.class);
                startActivity(intent);
            }
        });


    }

    /*public void redirectToCustomerReqGatheringActivity(View view){
        Intent intent = new Intent(this,CustomerReqGatheringActivity.class);
        // EditText editTextForUsername = findViewById(R.id.editText);
        //   EditText editTextForPassword = findViewById(R.id.editText2);
        //String username = editTextForUsername.getText().toString();
        // String password = editTextForPassword.getText().toString();
        // intent.putExtra("UserName", username);
        // intent.putExtra("Password", password);
        startActivity(intent);

    }

    public void redirectToCustomerViewListOfReqActivity(View view){
        Intent intent = new Intent(this,CustomerViewListOfReqActivity.class);
        startActivity(intent);

    }*/
}
