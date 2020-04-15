package com.sliit.project_elephas;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_new_sight extends AppCompatActivity {

    EditText txtsightID,txtsightName,txttpriceChild,txtpriceAlud;
    Button btnSave,btnShow,btnUpdate,btnDelete,btnshowall;
    DatabaseReference dbRef;
    sightseenticketprices sightPriceObj;


    //Firebase
    FirebaseDatabase firebaseDatabase;

    //Method to clear all user inputs
    private void clearControls(){
        txtsightID.setText("");
        txtsightName.setText("");
        txttpriceChild.setText("");
        txtpriceAlud.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sight);


        txtsightID = findViewById(R.id.sno1);
        txtsightName = findViewById(R.id.sname1);
        txttpriceChild = findViewById(R.id.childprice);
        txtpriceAlud = findViewById(R.id.adultprice);

        btnSave = findViewById(R.id.s_saveButton);
        btnShow = findViewById(R.id.s_showButton);
        btnUpdate = findViewById(R.id.s_updateButton);
        btnDelete = findViewById(R.id.s_deleteButton);
        btnshowall = findViewById(R.id.s_showAllButton);


        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("SightSeen");

        sightPriceObj = new sightseenticketprices();

        // save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              dbRef = FirebaseDatabase.getInstance().getReference().child("SightSeen");
                try {
                    if (TextUtils.isEmpty(txtsightID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a sight ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtsightName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a sight name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txttpriceChild.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter price for child ticket price", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtpriceAlud.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter price for adult ticket price", Toast.LENGTH_SHORT).show();
                    else {


                        // Take inputs from the user and assigning them in to std instance
                        sightPriceObj.setSightNo(txtsightID.getText().toString().trim());
                        sightPriceObj.setSightName(txtsightName.getText().toString().trim());
                        sightPriceObj.setTpriceChild(txttpriceChild.getText().toString().trim());
                        sightPriceObj.setTpriceAdult(txtpriceAlud.getText().toString().trim());


                        // Insert to the database
                        dbRef.push().setValue(sightPriceObj);
                        dbRef.child("Sight1").setValue(sightPriceObj);


                        // Feed to user via a toast
                        Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });




        // Show
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("SightSeen").child("Sight1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtsightID.setText(dataSnapshot.child("sightNo").getValue().toString());
                            txtsightName.setText(dataSnapshot.child("sightName").getValue().toString());
                            txttpriceChild.setText(dataSnapshot.child("tpriceChild").getValue().toString());
                            txtpriceAlud.setText(dataSnapshot.child("tpriceAdult").getValue().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        //show all data
        btnshowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = (Cursor) dbRef.getDatabase();
                if (res.getCount() == 0){
                    //show message
                    showMessage3("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("packageNo :"+res.getString(0)+"\n");
                    buffer.append("packageName :"+res.getString(1)+"\n");
                    buffer.append("Ticket Price For Child :"+res.getString(2)+"\n");
                    buffer.append("Ticket Price For Adult :"+res.getString(3)+"\n \n");

                }
                //show all data
                showMessage3("Data",buffer.toString());
                }
        });

        // Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("SightSeen");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Sight1")) {
                            try {
                                sightPriceObj.setSightNo(txtsightID.getText().toString().trim());
                                sightPriceObj.setSightName(txtsightName.getText().toString().trim());
                                sightPriceObj.setTpriceChild(txttpriceChild.getText().toString().trim());
                                sightPriceObj.setTpriceAdult( txtpriceAlud.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("SightSeen").child("Sight1");
                                dbRef.setValue(sightPriceObj);
                                clearControls();

                                // Feedback to the user via toast
                                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();


                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        // Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("SightSeen");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Sight1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("SightSeen").child("Sight1");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    public void showMessage3(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}

