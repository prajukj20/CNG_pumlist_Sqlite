package com.example.cngpumpnotifiction;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    public static String DB_NAME = "PUMPDB";
    public static int DB_VERSION = 9;

    public DBhelper(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Registeruser (id INTEGER PRIMARY KEY AUTOINCREMENT,Email_id TEXT,password TEXT,typpe TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS pumpadd (id INTEGER PRIMARY KEY AUTOINCREMENT,pumpname TEXT,address TEXT,location TEXT,time TEXT,usser_id TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Notification (id INTEGER PRIMARY KEY AUTOINCREMENT,message TEST,Updated_by TEXT,pump_id TEXT)");
    }

    public void addnotify(String message,String Updated_by,String pump_id){
        ContentValues values = new ContentValues();
        values.put("message", message);
        values.put("Updated_by", Updated_by);
        values.put("pump_id", pump_id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Notification",null,values);
        db.close();
    }

    @SuppressLint("Range")
    public List<notifymessage> getnotify(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qurey1 = "SELECT * FROM Notification";
        Cursor cursor = db.rawQuery(qurey1, new String[]{});
        List<notifymessage> notifyMSG = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
               String message = cursor.getString(cursor.getColumnIndex("message"));
                String Updated_by = cursor.getString(cursor.getColumnIndex("Updated_by"));
                String Pump_id = cursor.getString(cursor.getColumnIndex("pump_id"));

               notifyMSG.add(new notifymessage(message,Updated_by,Pump_id));
            }while (cursor.moveToNext());
        }

        return notifyMSG;
    }

    public void addpump (String pumpname,String address,String location,String time,String usser_id){
        ContentValues values = new ContentValues();
        values.put("pumpname", pumpname);
        values.put("address", address);
        values.put("location", location);
        values.put("time", time);
        values.put("usser_id", usser_id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("pumpadd",null,values);
        db.close();
    }
    @SuppressLint("Range")
    public List <pumplist>getregisterpump(){
        List<pumplist> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String qurey = "SELECT * FROM pumpadd";
        Cursor cursor = db.rawQuery(qurey,new String[]{});
        if (cursor.moveToFirst()){
            do {

                String id = cursor.getString(cursor.getColumnIndex("id"));
                String pumpname = cursor.getString(cursor.getColumnIndex("pumpname"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String usser_id = cursor.getString(cursor.getColumnIndex("usser_id"));

                list.add(new pumplist(id,pumpname,address,location,time,usser_id));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public void addregister(String Email_id,String password,String type )
    {
        ContentValues values = new ContentValues();
        values.put("Email_id", Email_id);
        values.put("password", password);
        values.put("typpe", type);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Registeruser", null, values);
        db.close();
    }
    @SuppressLint("Range")
    public List<userdetails> getregisterUserByEmail(String email){
        List<userdetails> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String qurey1 = "SELECT * FROM Registeruser WHERE Email_id = '"+email+"'";
        Cursor cursor = db.rawQuery(qurey1, new String[]{});
        if (cursor.moveToFirst()){
           do {
               String Id= cursor.getString(cursor.getColumnIndex("id"));
               String stremail= cursor.getString(cursor.getColumnIndex("Email_id"));
               String strpassword =cursor.getString(cursor.getColumnIndex("password"));
               String  strtyppe = cursor.getString(cursor.getColumnIndex("typpe"));

               list.add(new userdetails(Id,stremail,strpassword,strtyppe));

           }while (cursor.moveToNext());
        }
        return list;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pumpadd");
        onCreate(sqLiteDatabase);

    }
}
