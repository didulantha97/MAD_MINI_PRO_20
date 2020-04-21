package com.sliit.project_elephas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.activities.all_customer_payments;

public class AfterLoginCustomerActivity extends AppCompatActivity {

    private static String PASSPORT_NO = null;
    DatabaseHelper mDatabaseHelper;
    Button btnViewAll;
    Button payment, requirements;

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

        requirements = (Button) findViewById(R.id.button2);
        requirements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginCustomerActivity.this,CustomerReqGatheringActivity.class);

                //intent.putExtra("CUSTOMER_ID",);
                intent.putExtra("CUSTOMER_PASSPORT_NO",PASSPORT_NO);
                //intent.putExtra("CUSTOMER_EMAIL", );
                //intent.putExtra("CUSTOMER_NAME",);

                // EditText editTextForUsername = findViewById(R.id.editText);
                //   EditText editTextForPassword = findViewById(R.id.editText2);
                //String username = editTextForUsername.getText().toString();
                // String password = editTextForPassword.getText().toString();
                // intent.putExtra("UserName", username);
                // intent.putExtra("Password", password);
                startActivity(intent);
            }
        });


    }

    /*public void redirectToCustomerReqGatheringActivity(View view){
        PASSPORT_NO = getIntent().getExtras().getString("CUSTOMER_PASSPORT_NO");


        btnViewAll = (Button) findViewById(R.id.button3);
        mDatabaseHelper = new DatabaseHelper(this);
        //viewAll();
    }


    /*
    //viewAll method
    public void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mDatabaseHelper.getAllData();
                if (res.getCount() == 0) {
                    //show message
                    showMessage("Error","No Data Found");
                    //return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("CustomerID\t\t: " + res.getString(0)+"\n");
                    buffer.append("Name\t\t\t\t:" + res.getString(1)+"\n");
                    buffer.append("Nationality\t\t: " + res.getString(2)+"\n");
                    buffer.append("No of People\t\t: " + res.getString(3)+"\n");
                    buffer.append("No of Days\t\t:" + res.getString(6)+"\n");
                    buffer.append("Arrival Date\t\t: " + res.getString(4)+"\n");
                    buffer.append("Departure Date\t\t: " + res.getString(5)+"\n");
                    buffer.append("Hotel Category\t\t:" + res.getString(7)+"\n");
                    buffer.append("Remarks\t\t:" + res.getString(8)+"\n*** end of the record ***\n\n");
                }

                //show all data
                showMessage("Customer Requirements",buffer.toString());
            }
        });
    }




    //method to show messages
    public void showMessage(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
 */



    // redirection code using Intent
    public void redirectToCustomerReqGatheringActivity(View view){
        Intent intent = new Intent(this,CustomerReqGatheringActivity.class);

        //intent.putExtra("CUSTOMER_ID",);
        intent.putExtra("CUSTOMER_PASSPORT_NO",PASSPORT_NO);
        //intent.putExtra("CUSTOMER_EMAIL", );
        //intent.putExtra("CUSTOMER_NAME",);

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
        intent.putExtra("CUSTOMER_PASSPORT_NO",PASSPORT_NO);
        startActivity(intent);

    }
}
