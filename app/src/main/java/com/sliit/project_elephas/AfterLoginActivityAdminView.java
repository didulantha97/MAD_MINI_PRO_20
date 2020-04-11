package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sliit.project_elephas.activities.*;

public class AfterLoginActivityAdminView extends AppCompatActivity {

    Button payments_manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_admin_view);

        payments_manage = (Button)findViewById(R.id.button7);

        payments_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginActivityAdminView.this, Activity_manage_cost.class);
                startActivity(intent);
            }
        });

    }

}
