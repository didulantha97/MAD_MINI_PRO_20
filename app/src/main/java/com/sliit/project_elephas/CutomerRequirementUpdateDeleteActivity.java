package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CutomerRequirementUpdateDeleteActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete;
    DatabaseHelper mDatabaseHelper;
    TextView txtViewCustomerID;
    EditText Name,Nationality,NoOfPeople,ArrivalDate,DepartureDate,NoOfDays,StarCategory,Remarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomer_requirement_update_delete);

        btnUpdate = (Button) findViewById(R.id.button16);
        btnDelete = (Button) findViewById(R.id.button17);
        txtViewCustomerID = (TextView) findViewById(R.id.textView16);   // customerID

        Name = (EditText) findViewById(R.id.editText15);
        Nationality = (EditText) findViewById(R.id.editText16);
        NoOfPeople = (EditText) findViewById(R.id.editText17);
        ArrivalDate = (EditText) findViewById(R.id.editText18);
        DepartureDate = (EditText) findViewById(R.id.editText19);
        NoOfDays = (EditText) findViewById(R.id.editText20);
        StarCategory = (EditText) findViewById(R.id.editText21);
        Remarks = (EditText) findViewById(R.id.editText22);

        mDatabaseHelper = new DatabaseHelper(this);


        //button methods
        updateDataOnClick();    //methods - update button
        deleteDataOnClick();    //methods - delete button

    }


    //this method is for update button
    public void updateDataOnClick() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get values from the editText fields
                String id = txtViewCustomerID.getText().toString();
                String name = Name.getText().toString();
                String nationality = Nationality.getText().toString();
                String noOfPeople = NoOfPeople.getText().toString();
                String arrivalDate = ArrivalDate.getText().toString();
                String departureDate = DepartureDate.getText().toString();
                String noOfDays = NoOfDays.getText().toString();
                String starCategory = StarCategory.getText().toString();
                String remarks = Remarks.getText().toString();


                boolean isUpdate = mDatabaseHelper.updateData(id,name,nationality,noOfPeople,arrivalDate,departureDate,noOfDays,starCategory,remarks);

                if (isUpdate) {
                    //data insertion successful
                    Toast.makeText(getApplicationContext(),"Data Updated Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data updating
                    //redirect to view data activity
                    //code
                } else {
                    //something went wrong when data is updating
                    Toast.makeText(getApplicationContext(),"Error: Data Not Updated!",Toast.LENGTH_SHORT).show(); //display message
                }

            }
        });
    }


    //this method is to delete the record
    public void deleteDataOnClick() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtViewCustomerID.getText().toString();
                Integer deletedRows = mDatabaseHelper.deleteData(id);
                if (deletedRows > 0) {
                    Toast.makeText(getApplicationContext(),"Data Deleted Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data deleting
                } else {
                    Toast.makeText(getApplicationContext(),"Data Not Deleted!",Toast.LENGTH_SHORT).show(); //display message
                }
            }
        });
    }
}
