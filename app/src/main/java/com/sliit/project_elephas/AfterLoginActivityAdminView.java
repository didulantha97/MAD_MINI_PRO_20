package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AfterLoginActivityAdminView extends AppCompatActivity {

    Button btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_admin_view);

        btnViewData = (Button) findViewById(R.id.button4);

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerViewListOfReqActivity.class);
            }
        });
    }

    public void redirectWithDataToManageInformationForTours(View view){
        Intent intent = new Intent(this,ManageInformationForToursActivity.class);
        // EditText editTextForUsername = findViewById(R.id.editText);
        //   EditText editTextForPassword = findViewById(R.id.editText2);
        //String username = editTextForUsername.getText().toString();
        // String password = editTextForPassword.getText().toString();
        // intent.putExtra("UserName", username);
        // intent.putExtra("Password", password);
        startActivity(intent);

    }
}
