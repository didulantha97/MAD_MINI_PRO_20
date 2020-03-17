package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sliit.project_elephas.activities.all_customer_payments;

public class LoginActivity extends AppCompatActivity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AfterLoginCustomerActivity.class);
                startActivity(intent);
            }
        });



    }

    /*public void sendDataToOtherScreen(View view){

        //should check here if the user is an admin or a customer
        //if user = admin then go to AfterLoginActivityAdminView
        //else user = customer then go to AfterLoginCustomerActivity
        Intent intent = new Intent(this,AfterLoginCustomerActivity.class);
        EditText editTextForUsername = findViewById(R.id.editText);
        EditText editTextForPassword = findViewById(R.id.editText2);
        String username = editTextForUsername.getText().toString();
        String password = editTextForPassword.getText().toString();
        intent.putExtra("UserName", username);
        intent.putExtra("Password", password);
        startActivity(intent);

    }


    public void redirectToRegistrationActivity(View view){

        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);

    }*/

}
