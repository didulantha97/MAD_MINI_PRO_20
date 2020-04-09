package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    EditText UserOrAdmin,Password;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserOrAdmin = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the values from the EditText fields in the LoginActivity
                String userOrAdmin = UserOrAdmin.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (userOrAdmin.length() != 0 && password.length() != 0) {
                    findOutCustomerOrAdmin(userOrAdmin,password);
                } else  {
                    //if some fields are empty then display all are required
                    Toast.makeText(getApplicationContext(),"All fields are required!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void findOutCustomerOrAdmin(String userOrAdmin, String password) {

        Customer thisCustomer = mDatabaseHelper.getLoginCustomerCredentials(userOrAdmin, password);
        if (thisCustomer.getId() == 0) {
            Admin thisAdmin = mDatabaseHelper.getLoginAdminCredentials(userOrAdmin, password);
            if (thisAdmin.getId() == 0) {
                Toast.makeText(getApplicationContext(),"Invalid Credentials !",Toast.LENGTH_SHORT).show(); //display message
            } else  {
                //then the admin is found and it's login credentials are valid
                Intent intent = new Intent(getApplicationContext(),AfterLoginActivityAdminView.class);
                intent.putExtra("ADMIN_ID", thisAdmin.getId());
                intent.putExtra("ADMIN_PASSPORT_NO", thisAdmin.getPassportno());
                intent.putExtra("ADMIN_EMAIL", thisAdmin.getEmail());
                intent.putExtra("ADMIN_NAME", thisAdmin.getName());
                startActivity(intent);
            }
        } else {
            //then the customer is found and it's login credentials are valid
            Intent intent = new Intent(getApplicationContext(),AfterLoginCustomerActivity.class);
            intent.putExtra("CUSTOMER_ID", thisCustomer.getId());
            intent.putExtra("CUSTOMER_PASSPORT_NO", thisCustomer.getPassportno());
            intent.putExtra("CUSTOMER_EMAIL", thisCustomer.getEmail());
            intent.putExtra("CUSTOMER_NAME", thisCustomer.getName());
            //intent.putExtra("CUSTOMER_OBJECT",thisCustomer);
            startActivity(intent);
        }

    }



/*    public void sendDataToOtherScreen(View view){

        //should check here if the user is an admin or a customer
        //if user = admin then go to AfterLoginActivityAdminView
        //else user = customer then go to AfterLoginCustomerActivity
        //to view the admin view please change AfterLoginCustomerActivity.class to AfterLoginActivityAdminView.class
        //this user account management part is under construction
        Intent intent = new Intent(this,AfterLoginCustomerActivity.class);
        EditText editTextForUsername = findViewById(R.id.editText);
        EditText editTextForPassword = findViewById(R.id.editText2);
        String username = editTextForUsername.getText().toString();
        String password = editTextForPassword.getText().toString();
        intent.putExtra("UserName", username);
        intent.putExtra("Password", password);
        startActivity(intent);

    }

 */

    public void redirectToRegistrationActivity(View view){

        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);

    }


}
