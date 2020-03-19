package com.sliit.project_elephas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class other_cost_details extends AppCompatActivity {
    private Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_cost_details);

        but1 = (Button) findViewById(R.id.dubut9);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        Button showDialog = (Button) findViewById(R.id.dubut15);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(other_cost_details.this);
                View mView = getLayoutInflater().inflate(R.layout.add_otherpackages,null);
                final EditText packageno = (EditText) mView.findViewById(R.id.editText5);
                final EditText packagename = (EditText) mView.findViewById(R.id.editText6);
                EditText packagedecription = (EditText) mView.findViewById(R.id.editText7);
                final EditText packageprice = (EditText) mView.findViewById(R.id.editText8);
                Button madd = (Button) mView.findViewById(R.id.dubut16);

                madd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!packageno.getText().toString().isEmpty() && !packagename.getText().toString().isEmpty() && !packageprice.getText().toString().isEmpty()){
                            Toast.makeText(other_cost_details.this,"Add New Package Successfuly",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(other_cost_details.this,"Please fill any empty fields",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mbuilder.setView(mView);
                AlertDialog dialog = mbuilder.create();
                dialog.show();
            }
        });
    }

    public void openActivity1() {


        Intent intent1 = new Intent(this, other_packages.class);
        startActivity(intent1);
    }
}

