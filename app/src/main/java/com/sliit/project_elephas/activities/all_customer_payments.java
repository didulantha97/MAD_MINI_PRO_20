package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sliit.project_elephas.AfterLoginCustomerActivity;
import com.sliit.project_elephas.LoginActivity;
import com.sliit.project_elephas.R;

public class all_customer_payments extends AppCompatActivity {

    Button pay_invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customer_payments);

        pay_invoice = (Button) findViewById(R.id.pay_inv);

        pay_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_customer_payments.this, Payment.class);
                startActivity(intent);
            }
        });

    }
}
