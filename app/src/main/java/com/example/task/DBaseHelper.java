package com.example.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBaseHelper extends SQLiteOpenHelper {
    public DBaseHelper(Context context) {
        super(context, "Userdata.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails (name TEXT primary key, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public boolean insertData(String name, String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        long result = DB.insert("Userdetails",null,contentValues);
        return result!=-1;
    }
    public boolean updateData(String name, String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[] {name});
        if(cursor.getCount()>0){
            long result = DB.update("Userdetails",contentValues,"name=?",new String[] {name});
            return result!=-1;
        }
        return  false;
    }
    public boolean deleteData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[] {name});
        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails","name=?",new String[] {name});
            return result!=-1;
        }
        return false;
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }
}
