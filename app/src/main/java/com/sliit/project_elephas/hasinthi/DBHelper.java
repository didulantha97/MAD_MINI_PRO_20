package com.sliit.project_elephas.hasinthi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String Hotel_TABLE_NAME = "hotel";
    public static final String Hotel_COLUMN_ID = "id";
    public static final String Hotel_COLUMN_NAME = "name";
    public static final String Hotel_COLUMN_Address = "address";
    public static final String Hotel_COLUMN_EMAIL = "email";
    public static final String Hotel_COLUMN_PHONE = "phone";
    public static final String Hotel_COLUMN_STARCLASS = "starclass";
    public static final String Hotel_COLUMN_SINGLE = "single";
    public static final String Hotel_COLUMN_DOUBLE= "double";
    public static final String Hotel_COLUMN_TRIPLE = "triple";
    public static final String Hotel_COLUMN_KING= "king";
    public static final String Hotel_COLUMN_QUARD = "quard";
    public static final String Hotel_COLUMN_QUEEN = "queen";
    public static final String Hotel_COLUMN_ROOMONLY= "roomonly";
    public static final String Hotel_COLUMN_BEDANDBREACKFAST = "bedandbreackfast";
    public static final String Hotel_COLUMN_FULLBOARD= "fullboard";
    public static final String Hotel_COLUMN_HALFBOARD= "halfboard";
    private HashMap hp;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
        "create table contacts " +
                "(id integer primary key, name text,phone text,email text,address text,starclass text, single text, Double text, triple text, king text, quard text, queen text, roomonly text, bedandbreackfast text, fullboard text, halfboard text)"
      );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Hotel");
        onCreate(db);
    }

    public boolean insertHotel (String name,String address, String phone, String email, String starclass,String single,String Double,String triple,String king, String quard, String queen, String roomonly, String bedandbreackfast, String fullboard, String halfboard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("starclass", starclass);
        contentValues.put("single", single);
        contentValues.put("Double", Double);
        contentValues.put("triple", triple);
        contentValues.put("king", king);
        contentValues.put("quard", quard);
        contentValues.put("queen", queen);
        contentValues.put("roomonly", roomonly);
        contentValues.put("bedandbreackfast", bedandbreackfast);
        contentValues.put("fullboard", fullboard);
        contentValues.put("halfboard", halfboard);

        db.insert("hotel", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Hotel_TABLE_NAME);
        return numRows;
    }

    public boolean updateHotel (Integer id, String name,String address, String phone, String email,String starclass,String single,String Double,String triple,String king, String quard, String queen, String roomonly, String bedandbreackfast, String fullboard, String halfboard)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("starclass", starclass);
        contentValues.put("single", single);
        contentValues.put("Double", Double);
        contentValues.put("triple", triple);
        contentValues.put("king", king);
        contentValues.put("quard", quard);
        contentValues.put("queen", queen);
        contentValues.put("roomonly", roomonly);
        contentValues.put("bedandbreackfast", bedandbreackfast);
        contentValues.put("fullboard", fullboard);
        contentValues.put("halfboard", halfboard);

        db.update("hotel", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public Integer deleteHotel (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllViewHotelDetails() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Hotel_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }


}
