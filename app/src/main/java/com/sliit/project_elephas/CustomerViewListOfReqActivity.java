package com.sliit.project_elephas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomerViewListOfReqActivity extends AppCompatActivity {

    private static final String TAG = "CustomerViewListOfReqActivity";

    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "NATIONALITY";
    public static final String COL4 = "NOOFPEOPLE";
    public static final String COL5 = "ARRIVALDATE";
    public static final String COL6 = "DEPARTUREDATE";
    public static final String COL7 = "NOOFDAYS";
    public static final String COL8 = "STARCATEGORY";
    public static final String COL9 = "REMARKS";

    DatabaseHelper mDatabaseHelper;
    /*
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;
     */


    private ListView lvCustomerReq;
    private CustomerRequirementListAdapter adapter;
    private List<CustomerRequirement> mCustomerRequirementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_list_of_req);

        lvCustomerReq = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        mCustomerRequirementList = new ArrayList<>();

        //data should be retrieved from the database - using Cursor object
        // Cursor obj data should be mapped here
        viewData();


        lvCustomerReq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //When user clicks on a record, do something here
                Toast.makeText(getApplicationContext(),"Clicked! Customer Req.ID : "+view.getTag(),Toast.LENGTH_SHORT).show();

                //CustomeRequirement Obj data should be pass to next activity
                //Intent intent = new Intent(getApplicationContext(),CutomerRequirementUpdateDeleteActivity.class);
                //startActivity(intent);
            }
        });





        /*
        mDatabaseHelper = new DatabaseHelper(this);
        userlist = findViewById(R.id.listView);
        listItem = new ArrayList<>();
         */
        //viewData();

        /*
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userlist.getItemAtPosition(1).toString();
                Toast.makeText(getApplicationContext(),""+text,Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(getApplicationContext(),CustomerReqGatheringActivity.class);
                //intent.putExtra("CustomerRequirementID", text);
                //startActivity(intent);
            }
        });
         */

    }


    private void viewData() {
        Cursor cursor = mDatabaseHelper.getAllData();

        if (cursor.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No data to show",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(COL1));
                String name = cursor.getString(cursor.getColumnIndex(COL2));
                String nationality = cursor.getString(cursor.getColumnIndex(COL3));
                String noOfPeople = cursor.getString(cursor.getColumnIndex(COL4));
                String noOfDays = cursor.getString(cursor.getColumnIndex(COL7));
                String arrivalDate = cursor.getString(cursor.getColumnIndex(COL5));
                String departureDate = cursor.getString(cursor.getColumnIndex(COL6));
                String starCategory = cursor.getString(cursor.getColumnIndex(COL8));
                String remark = cursor.getString(cursor.getColumnIndex(COL9));

                // retrieved data should be mapped here
                mCustomerRequirementList.add(new CustomerRequirement(Integer.parseInt(id),name,nationality,noOfPeople,noOfDays,arrivalDate,departureDate,starCategory,remark));
            }

            cursor.close();

            adapter = new CustomerRequirementListAdapter(getApplicationContext(),mCustomerRequirementList);
            lvCustomerReq.setAdapter(adapter);
        }


    }

    /*
    private void viewData() {
        Cursor cursor = mDatabaseHelper.getAllData();

        if (cursor.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No data to show",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
                listItem.add(cursor.getString(3));
                listItem.add(cursor.getString(4));
                listItem.add(cursor.getString(5));
                listItem.add(cursor.getString(6));
                listItem.add(cursor.getString(7));
                listItem.add(cursor.getString(8));
            }

            adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adapter);
        }
    }
    */

}
