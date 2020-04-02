package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerReqGatheringActivity extends AppCompatActivity {

    private static final String TAG = "CustomerReqGatheringActivity";
    DatabaseHelper mDatabaseHelper;
    Button btnSave;
    Button btnCancel;
    EditText Name,Nationality,NoOfPeople,ArrivalDate,DepartureDate,NoOfDays,StarCategory,Remarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_req_gathering);

        /*
        Intent intent = getIntent();
        int CustomerRequirementID = intent.getIntExtra("CustomerRequirementID", 0);
        if (CustomerRequirementID != 0) {
            Button btnUpdate = (Button) findViewById(R.id.button9);
            btnUpdate.setText("Update");

            Button btnDelete = (Button) findViewById(R.id.button10);
            btnDelete.setText("Delete");

            //UpdateData();
            //DeleteData();
        }
        */

        Name = (EditText) findViewById(R.id.editText3);
        Nationality = (EditText) findViewById(R.id.editText4);
        NoOfPeople = (EditText) findViewById(R.id.editText5);
        ArrivalDate = (EditText) findViewById(R.id.editText11);
        DepartureDate = (EditText) findViewById(R.id.editText12);
        NoOfDays = (EditText) findViewById(R.id.editText6);
        StarCategory = (EditText) findViewById(R.id.editText7);
        Remarks = (EditText) findViewById(R.id.editText13);
        btnSave = (Button) findViewById(R.id.button9);
        mDatabaseHelper = new DatabaseHelper(this);

        AddData();

    }

    /*
    //these methods will not be useful because it is used in cusReqUpdateDeleteActivity
    public void DeleteData() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

 //these methods will not be useful because it is used in cusReqUpdateDeleteActivity
    public void UpdateData(String CustomerRequirementID) {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    */

    public void AddData () {
        //when save button triggers this method will run
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting values form the form fields (editTexts) as String values put those into a String variable for each
                String name = Name.getText().toString();
                String nationality = Nationality.getText().toString();
                String noOfPeople = NoOfPeople.getText().toString();
                String arrivalDate = ArrivalDate.getText().toString();
                String departureDate = DepartureDate.getText().toString();
                String noOfDays = NoOfDays.getText().toString();
                String starCategory = StarCategory.getText().toString();
                String remarks = Remarks.getText().toString();

                //check all the fields are not empty
                if (name.length() !=0 && nationality.length() != 0 && noOfPeople.length() != 0 && arrivalDate.length() != 0 && departureDate.length() != 0 && noOfDays.length() != 0 && starCategory.length() != 0 && remarks.length() != 0) {
                    boolean isInserted = mDatabaseHelper.insertData(name, nationality, noOfPeople, arrivalDate, departureDate, noOfDays, starCategory, remarks);

                    if (isInserted == true) {
                        //data insertion successful
                        Toast.makeText(getApplicationContext(),"Data Inserted Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data insertion
                        resetForm();    //after successful data insertion all form fields should be empty
                    } else {
                        //something went wrong when data is inserted
                        Toast.makeText(getApplicationContext(),"Error: Data Not Inserted!",Toast.LENGTH_SHORT).show(); //display message
                    }
                } else {
                    //if some fields are empty then display all are required
                    Toast.makeText(getApplicationContext(),"All fields are required!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetForm() {
        //form fields are set as null / empty
        Name.setText("");
        Nationality.getText().toString();
        NoOfPeople.getText().toString();
        ArrivalDate.getText().toString();
        DepartureDate.getText().toString();
        NoOfDays.getText().toString();
        StarCategory.getText().toString();
        Remarks.getText().toString();
    }

}
