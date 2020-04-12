package com.sliit.project_elephas.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sliit.project_elephas.model.Driver;
import com.sliit.project_elephas.model.Invoice;
import com.sliit.project_elephas.model.Payments;
import com.sliit.project_elephas.model.Transport;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "projectElephas.db";

    //Payment Table create
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Database_payment.paymentEntry.TABLE_NAME + " (" +
                    Database_payment.paymentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_payment.paymentEntry.COL_1 + " TEXT," +
                    Database_payment.paymentEntry.COL_2 + " TEXT," +
                    Database_payment.paymentEntry.COL_3 + " TEXT," +
                    Database_payment.paymentEntry.COL_4 + " TEXT," +
                    Database_payment.paymentEntry.COL_5 + " TEXT," +
                    Database_payment.paymentEntry.COL_6 + " TEXT," +
                    Database_payment.paymentEntry.COL_7 + " DOUBLE)";

    // drop table sql query
    private String DROP_PAYMENT_TABLE = "DROP TABLE IF EXISTS " + Database_payment.paymentEntry.TABLE_NAME;

    //Transport table
    private static final String SQL_CREATE_TRANSPORT_TABLE =
            "CREATE TABLE " + Database_transport.transportEntry.TABLE_TRANSPORT + " (" +
                    Database_transport.transportEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_transport.transportEntry.COL_1 + " INTEGER," +
                    Database_transport.transportEntry.COL_2 + " DOUBLE," +
                    Database_transport.transportEntry.COL_3 + " DOUBLE," +
                    Database_transport.transportEntry.COL_4 + " DOUBLE," +
                    Database_transport.transportEntry.COL_5 + " DOUBLE," +
                    Database_transport.transportEntry.COL_6 + " DOUBLE)";

    // drop table sql query
    private String DROP_TRANSPORT_TABLE = "DROP TABLE IF EXISTS " + Database_transport.transportEntry.TABLE_TRANSPORT;

    //Driver table
    private static final String SQL_CREATE_DRIVER_TABLE =
            "CREATE TABLE " + Database_driver.driverEntry.TABLE_DRIVER + " (" +
                    Database_driver.driverEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_driver.driverEntry.COL_1 + " INTEGER," +
                    Database_driver.driverEntry.COL_2 + " DOUBLE," +
                    Database_driver.driverEntry.COL_3 + " DOUBLE," +
                    Database_driver.driverEntry.COL_4 + " DOUBLE)";

    // drop table sql query
    private String DROP_TABLE_DRIVER = "DROP TABLE IF EXISTS " + Database_driver.driverEntry.TABLE_DRIVER;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_TRANSPORT_TABLE);
        db.execSQL(SQL_CREATE_DRIVER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DROP_PAYMENT_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /*
    Author : Isara
    payment function start from here !
    */

    //add payments
    public boolean addCustomerpayments(Payments payments) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Database_payment.paymentEntry.COL_1, payments.getUser_email());
        values.put(Database_payment.paymentEntry.COL_2, payments.getName());
        values.put(Database_payment.paymentEntry.COL_3, payments.getAddress());
        values.put(Database_payment.paymentEntry.COL_4, payments.getNic());
        values.put(Database_payment.paymentEntry.COL_5, payments.getCard());
        values.put(Database_payment.paymentEntry.COL_7, payments.getAmount());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Database_payment.paymentEntry.TABLE_NAME, null, values);

        db.close();

        return true;

    }

    //read payment ID to spinner
    public ArrayList<Payments> readAllPayments(){

        ArrayList<Payments> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();

        Cursor results = scoredb.rawQuery("select * from "+ Database_payment.paymentEntry.TABLE_NAME,null);

        results.moveToFirst();

        while (results.isAfterLast()==false){

            Payments smodel= new Payments();

            smodel.setID(results.getInt(results.getColumnIndex(Database_payment.paymentEntry._ID)));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Payments> selectedPayment(String id){

        ArrayList<Payments> models= new ArrayList<>();
        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_payment.paymentEntry.TABLE_NAME +" where "+Database_payment.paymentEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Payments smodel= new Payments();

            smodel.setUser_email(results.getString(1));
            smodel.setName(results.getString(2));
            smodel.setAddress(results.getString(3));
            smodel.setNic(results.getString(4));
            smodel.setCard(results.getString(5));
            smodel.setAmount(Double.parseDouble(results.getString(7)));
            smodel.setStatus(results.getString(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //if status is verified
    public boolean updatePayment(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("status","Verified");

        long res=db.update(Database_payment.paymentEntry.TABLE_NAME,contentValues,Database_payment.paymentEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete payment
    public boolean refundPayment(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_payment.paymentEntry.TABLE_NAME, Database_payment.paymentEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Transport DB helper start here
    //add payments
    public boolean addtransport(Transport transport) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Database_transport.transportEntry.COL_1, transport.getPassport());
        values.put(Database_transport.transportEntry.COL_2, transport.getGrossMileage());
        values.put(Database_transport.transportEntry.COL_3, transport.getExtraMileage());
        values.put(Database_transport.transportEntry.COL_4, transport.getTotalMileage(transport.getGrossMileage(),transport.getExtraMileage()));
        values.put(Database_transport.transportEntry.COL_5, transport.getChargePerKm());


        //values.put(Database_transport.transportEntry.COL_6, transport.getTotalUsd(transport.getTotalMileage(),transport.getExtraMileage()));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Database_transport.transportEntry.TABLE_TRANSPORT, null, values);

        db.close();

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    //read payment ID to spinner
    public ArrayList<Transport> readAllTransports(){

        ArrayList<Transport> models= new ArrayList<>();

        SQLiteDatabase score = this.getReadableDatabase();

        Cursor results = score.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT,null);

        results.moveToFirst();

        while (results.isAfterLast()==false){

            Transport transmodel= new Transport();

            transmodel.setID(results.getInt(results.getColumnIndex(Database_transport.transportEntry._ID)));
            models.add(transmodel);
            results.moveToNext();
        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Transport> selectedTransports(String id){

        ArrayList<Transport> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT +" where "+Database_transport.transportEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Transport smodel= new Transport();
            smodel.setPassport(results.getInt(1));
            smodel.setGrossMileage(results.getDouble(2));
            smodel.setExtraMileage(results.getDouble(3));
            smodel.setTotalMileage(results.getDouble(4));
            smodel.setChargePerKm(results.getDouble(5));
            smodel.setTotalUsd(results.getDouble(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //Update transport
    public boolean updateTransport(String id,int passport, double gross, double extra, double chargeKm, double totalDistance, double totalUsd){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(Database_transport.transportEntry.COL_1, passport);
        contentValues.put(Database_transport.transportEntry.COL_2, gross);
        contentValues.put(Database_transport.transportEntry.COL_3, extra);
        contentValues.put(Database_transport.transportEntry.COL_4, totalDistance);
        contentValues.put(Database_transport.transportEntry.COL_5, chargeKm);
        contentValues.put(Database_transport.transportEntry.COL_6, totalUsd);

        //contentValues.put("status","Verified");

        long res=db.update(Database_transport.transportEntry.TABLE_TRANSPORT,contentValues,Database_transport.transportEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete transport
    public boolean deletetransport(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_transport.transportEntry.TABLE_TRANSPORT, Database_transport.transportEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Add driver cost
    public boolean addDriver(Driver driver) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Database_driver.driverEntry.COL_1, driver.getPassport());
        values.put(Database_driver.driverEntry.COL_2, driver.getDriver());
        values.put(Database_driver.driverEntry.COL_3, driver.getGuide());
        values.put(Database_driver.driverEntry.COL_4, driver.getTotal_in_usd(driver.getDriver(),driver.getGuide()));

        // Insert the new row, returning the primary key value of the new row
        //long newRowId = db.insert(Database_Driver_Guide.DriverEntry.TABLE_DRIVER, null, values);
        long newRowId = db.insert(Database_driver.driverEntry.TABLE_DRIVER, null, values);

        //db.close();

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    //read payment ID to spinner
    public ArrayList<Driver> readAllDrivers(){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase score = this.getReadableDatabase();

        Cursor results = score.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER,null);

        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver driver = new Driver();

            driver.setID(results.getInt(results.getColumnIndex(Database_driver.driverEntry._ID)));

            models.add(driver);
            results.moveToNext();
        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Driver> selectedDrivers(String id){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER +" where "+Database_driver.driverEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver smodel= new Driver();
            smodel.setPassport(results.getInt(1));
            smodel.setDriver(results.getDouble(2));
            smodel.setGuide(results.getDouble(3));
            smodel.setTotal_in_usd(results.getDouble(4));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //Update transport
    public boolean updateDriver(String id,int passport, double driver, double guide, double totalUsd){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(Database_driver.driverEntry.COL_1, passport);
        contentValues.put(Database_driver.driverEntry.COL_2, driver);
        contentValues.put(Database_driver.driverEntry.COL_3, guide);
        contentValues.put(Database_driver.driverEntry.COL_4, totalUsd);

        //contentValues.put("status","Verified");

        long res=db.update(Database_driver.driverEntry.TABLE_DRIVER,contentValues,Database_driver.driverEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete transport
    public boolean deleteDriver(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_driver.driverEntry.TABLE_DRIVER, Database_driver.driverEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }


    /*public ArrayList<Driver> selectedInvoiceDriver(String passport){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER +" where "+Database_driver.driverEntry.COL_1 +" = '"+passport+"'",null);
        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver smodel= new Driver();

            smodel.setTotal_in_usd(results.getDouble(4));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    public ArrayList<Transport> selectedInvoiceTransports(String passport){

        ArrayList<Transport> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT +" where "+Database_transport.transportEntry.COL_1 +" = '"+passport+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Transport smodel= new Transport();
            smodel.setTotalUsd(results.getDouble(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    public ArrayList<Invoice> selectedInvoice(String passport){
        ArrayList<Invoice> models= new ArrayList<>();

        selectedInvoiceDriver(passport);



        return  models;


    }*/


    public List<Invoice> selectedInvoice(String passport){

        List<Invoice> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();

        /*Cursor results = scoredb.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT +" where "+Database_transport.transportEntry.COL_1 +" = '"+passport+"'",null);
*/
        Cursor results = scoredb.rawQuery("select  t.passport, t.total_usd, d.total_usd from transport_cost t,driver_guide d where t."+Database_transport.transportEntry.COL_1 +" = '"+passport+"' and t.passport == d.passport",null);

        results.moveToFirst();

        while (results.isAfterLast()==false){

            Invoice smodel= new Invoice();
            smodel.setPassport(results.getInt(results.getColumnIndex("passport")));
            smodel.setTransport(results.getDouble(results.getColumnIndex("total_USD")));
            smodel.setDriver(results.getDouble(results.getColumnIndex("total_usd")));

            //smodel.setTotoalCost(results.getDouble(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }




}
