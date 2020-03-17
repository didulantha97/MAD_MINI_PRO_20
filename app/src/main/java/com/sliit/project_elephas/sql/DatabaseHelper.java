package com.sliit.project_elephas.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sliit.project_elephas.model.Payments;
import com.sliit.project_elephas.model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL("create table payment(ID INTEGER PRIMARY KEY AUTOINCREMENT,user_email text,name text,address text,nic text,amount double,card text,status text)");
        /*db.execSQL("create table booking(ID INTEGER PRIMARY KEY AUTOINCREMENT,location text,person int,days int,email text,status text)");
        db.execSQL("create table feedback(ID INTEGER PRIMARY KEY AUTOINCREMENT,user_email text,feedback text,reply text)");*/
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }



    //Starting User Database Functions
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public ArrayList<User> readAllUsers(){

        ArrayList<User> models= new ArrayList<>();
        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from user",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            User smodel= new User();

            smodel.setId(results.getInt(0));
            smodel.setName(results.getString(1));
            models.add(smodel);
            results.moveToNext();

        }

        return models;
    }


    public ArrayList<User> selectedUser(String id){

        ArrayList<User> models= new ArrayList<>();
        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from user where user_id='"+id+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            User smodel= new User();
            smodel.setName(results.getString(1));
            smodel.setEmail(results.getString(2));
            smodel.setPassword(results.getString(3));
            models.add(smodel);
            results.moveToNext();

        }

        return models;
    }

    public boolean updateUsers(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("user_name",user.getName());
        contentValues.put("user_email",user.getEmail());
        contentValues.put("user_password",user.getPassword());

        long res=db.update("user",contentValues,"user_id='"+user.getId()+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    public boolean deleteUsers(String id){

        SQLiteDatabase db = getReadableDatabase();

        long res=db.delete("user","user_id='"+id+"'", null);

        db.close();

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //Start payment Functions
    public boolean insert_payment(Payments payment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_email",payment.getUser_email());
        contentValues.put("name",payment.getName());
        contentValues.put("address",payment.getAddress());
        contentValues.put("nic",payment.getNic());
        contentValues.put("amount",payment.getAmount());
        contentValues.put("card",payment.getCard());
        contentValues.put("status","pending");


        long result = db.insert("payment",null,contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }

    }

    public boolean updatePayment(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("status","Verified");

        long res=db.update("payment",contentValues,"ID='"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    public ArrayList<Payments> selectedPayment(String id){

        ArrayList<Payments> models= new ArrayList<>();
        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from payment where id='"+id+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Payments smodel= new Payments();
            smodel.setName(results.getString(2));
            smodel.setAddress(results.getString(3));
            smodel.setNic(results.getString(4));
            smodel.setCard(results.getString(6));
            smodel.setAmount(Double.parseDouble(results.getString(5)));
            smodel.setStatus(results.getString(7));
            models.add(smodel);
            results.moveToNext();

        }

        return models;
    }

    public ArrayList<Payments> readAllPayments(){

        ArrayList<Payments> models= new ArrayList<>();
        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from user",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Payments smodel= new Payments();

            smodel.setID(results.getInt(0));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    public boolean refundPayment(String id){

        SQLiteDatabase db = getReadableDatabase();

        long res=db.delete("payment","ID='"+id+"'", null);

        db.close();

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }


}
