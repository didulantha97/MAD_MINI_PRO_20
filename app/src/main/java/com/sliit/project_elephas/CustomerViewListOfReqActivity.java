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

public class CustomerViewListOfReqActivity extends AppCompatActivity {

    private static final String TAG = "CustomerViewListOfReqActivity";
    DatabaseHelper mDatabaseHelper;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_list_of_req);

        mDatabaseHelper = new DatabaseHelper(this);
        userlist = findViewById(R.id.listView);
        listItem = new ArrayList<>();
        viewData();

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

    }

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


}
