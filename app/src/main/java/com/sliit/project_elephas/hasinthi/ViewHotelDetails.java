package com.sliit.project_elephas.hasinthi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.R;

import static com.sliit.project_elephas.R.id.editText;
import static com.sliit.project_elephas.R.id.editText2;
import static com.sliit.project_elephas.R.id.editText3;
import static com.sliit.project_elephas.R.id.editText5;
import static com.sliit.project_elephas.R.id.editText6;

public class ViewHotelDetails extends AppCompatActivity {


    private DBHelper mydb ;

    TextView name ;
    TextView address;
    TextView phone;
    TextView email;
    TextView starclass;
    TextView single;
    TextView Double;
    TextView triple;
    TextView king;
    TextView quard;
    TextView queen;
    TextView roomonly;
    TextView bedandbreackfast;
    TextView fullboard;
    TextView halfboard;
    int id_To_Update = 0;
    private Object CharSequence;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotel_details);

        name = (TextView) findViewById(editText);
        address= (TextView) findViewById(editText2);
        phone = (TextView) findViewById(editText3);
        email = (TextView) findViewById(R.id.editText4);
        starclass = (TextView) findViewById(editText5);
        single = (TextView) findViewById(editText6);
        Double = (TextView) findViewById(R.id.editText7);
        triple = (TextView) findViewById(R.id.editText8);
        king = (TextView) findViewById(R.id.editText10);
        quard = (TextView) findViewById(R.id.editText9);
        queen = (TextView) findViewById(R.id.editText11);
        bedandbreackfast= (TextView) findViewById(R.id.editText16);
        roomonly= (TextView) findViewById(R.id.editText12);
        fullboard = (TextView) findViewById(R.id.editText15);
        halfboard = (TextView) findViewById(R.id.editText14);


        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String addres = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String emai= rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String starclas = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String singl = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String Doubl = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String tripl = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String kin = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String quar = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String quee = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String roomonl = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String bedandbreackfas = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String fullboar = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));
                String halfboar = rs.getString(rs.getColumnIndex(DBHelper.Hotel_COLUMN_NAME));


                if (!rs.isClosed())  {
                    rs.close();
                }

                Button b = (Button)findViewById(R.id.button);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence)nam);
                name.setFocusable(false);
                name.setClickable(false);

                address.setText((CharSequence)addres);
                address.setFocusable(false);
                address.setClickable(false);

                phone.setText((CharSequence)phon);
                phone.setFocusable(false);
                phone.setClickable(false);

                email.setText((CharSequence)emai);
                email.setFocusable(false);
                email.setClickable(false);

                starclass.setText((CharSequence)starclas);
                starclass.setFocusable(false);
                starclass.setClickable(false);

                single.setText((CharSequence) singl);
                single.setFocusable(false);
                single.setClickable(false);

                Double.setText((CharSequence)Doubl);
                Double.setFocusable(false);
                Double.setClickable(false);

                triple.setText((CharSequence)tripl);
                triple.setFocusable(false);
                triple.setClickable(false);

                king.setText((CharSequence)kin);
                king.setFocusable(false);
                king.setClickable(false);

                quard.setText((CharSequence) quar);
                quard.setFocusable(false);
                quard.setClickable(false);

                queen.setText((CharSequence)quee);
                queen.setFocusable(false);
                queen.setClickable(false);

                roomonly.setText((CharSequence)roomonl);
                roomonly.setFocusable(false);
                roomonly.setClickable(false);

                bedandbreackfast.setText((CharSequence)bedandbreackfas);
                bedandbreackfast.setFocusable(false);
                bedandbreackfast.setClickable(false);

                fullboard.setText((CharSequence)fullboar);
                fullboard.setFocusable(false);
                fullboard.setClickable(false);

                halfboard.setText((CharSequence)halfboar);
                halfboard.setFocusable(false);
                halfboard.setClickable(false);


            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_hotel, menu);
            } else{
                getMenuInflater().inflate(R.menu.main_manu,menu);
            }
        }
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Hotel:
                Button b = (Button)findViewById(R.id.button);
                b.setVisibility(View.VISIBLE);

                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                address.setEnabled(true);
                address.setFocusableInTouchMode(true);
                address.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);

                starclass.setEnabled(true);
                starclass.setFocusableInTouchMode(true);
                starclass.setClickable(true);

                single.setEnabled(true);
                single.setFocusableInTouchMode(true);
                single.setClickable(true);

                Double.setEnabled(true);
                Double.setFocusableInTouchMode(true);
                Double.setClickable(true);

                triple.setEnabled(true);
                triple.setFocusableInTouchMode(true);
                triple.setClickable(true);

                king.setEnabled(true);
                king.setFocusableInTouchMode(true);
                king.setClickable(true);

                quard.setEnabled(true);
                quard.setFocusableInTouchMode(true);
                quard.setClickable(true);

                queen.setEnabled(true);
                queen.setFocusableInTouchMode(true);
                queen.setClickable(true);

                roomonly.setEnabled(true);
                roomonly.setFocusableInTouchMode(true);
                roomonly.setClickable(true);

                bedandbreackfast.setEnabled(true);
                bedandbreackfast.setFocusableInTouchMode(true);
                bedandbreackfast.setClickable(true);

                fullboard.setEnabled(true);
                fullboard.setFocusableInTouchMode(true);
                fullboard.setClickable(true);

                halfboard.setEnabled(true);
                halfboard.setFocusableInTouchMode(true);
                halfboard.setClickable(true);


                return true;
            case R.id.Delete_Hotel:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteHotel)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteHotel(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateHotel(id_To_Update,name.getText().toString(),address.getText().toString(),
                        phone.getText().toString(), email.getText().toString(),starclass.getText().toString(),single.getText().toString(),Double.getText().toString(),
                        triple.getText().toString(),king.getText().toString(),quard.getText().toString(),queen.getText().toString(),roomonly.getText().toString(),bedandbreackfast.getText().toString(),fullboard.getText().toString(),halfboard.getText().toString()   )){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertHotel(name.getText().toString(),address.getText().toString(),
                        phone.getText().toString(), email.getText().toString(),starclass.getText().toString(),single.getText().toString(),Double.getText().toString(),
                        triple.getText().toString(),king.getText().toString(),quard.getText().toString(),queen.getText().toString(),roomonly.getText().toString(),bedandbreackfast.getText().toString(),fullboard.getText().toString(),halfboard.getText().toString()     )){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }

}
