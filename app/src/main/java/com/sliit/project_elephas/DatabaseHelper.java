package com.sliit.project_elephas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ElephasVacations.db";
    public static final String TABLE_NAME = "customer_requirement_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "NATIONALITY";
    public static final String COL4 = "NOOFPEOPLE";
    public static final String COL5 = "ARRIVALDATE";
    public static final String COL6 = "DEPARTUREDATE";
    public static final String COL7 = "NOOFDAYS";
    public static final String COL8 = "STARCATEGORY";
    public static final String COL9 = "REMARKS";

    public static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+" ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2+" TEXT NOT NULL,"+COL3+" TEXT NOT NULL,"+COL4+" TEXT NOT NULL,"+COL5+" TEXT NOT NULL,"+COL6+" TEXT NOT NULL,"+COL7+" TEXT NOT NULL,"+COL8+" TEXT NOT NULL,"+COL9+" TEXT NOT NULL)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); //checking purpose
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String nationality,String noofpeople,String arrivaldata,String departuredate,String noofdays,String starcategory,String remarks) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,nationality);
        contentValues.put(COL4,noofpeople);
        contentValues.put(COL5,arrivaldata);
        contentValues.put(COL6,departuredate);
        contentValues.put(COL7,noofdays);
        contentValues.put(COL8,starcategory);
        contentValues.put(COL9,remarks);
        long result =  db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }


    //view data
    public Cursor getAllData() {
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String nationality,String noofpeople,String arrivaldata,String departuredate,String noofdays,String starcategory,String remarks) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,nationality);
        contentValues.put(COL4,noofpeople);
        contentValues.put(COL5,arrivaldata);
        contentValues.put(COL6,departuredate);
        contentValues.put(COL7,noofdays);
        contentValues.put(COL8,starcategory);
        contentValues.put(COL9,remarks);
        long result =  db.insert(TABLE_NAME,null,contentValues);

        return result != -1;
    }
}
