package com.example.xipproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.jar.Attributes;

public class DatabaseHelper  extends SQLiteOpenHelper {

    Context c;
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "assetName";
    public static final String COL_4 = "selectDate";
    public static final String COL_5 = "startTime";
    public static final String COL_6 = "endTime";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


 //   SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);


    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,assetName TEXT,selectDate TEXT ,startTime TEXT,endTime TEXT,UNIQUE (assetName,selectDate,startTime,endTime))");


        }catch (Exception e){

            Log.e( "MYDB","Table Creation Error",e );
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name,String assetName,String selectDate,String startTime,String endTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,assetName);
        contentValues.put(COL_4,selectDate);
        contentValues.put(COL_5,startTime);
        contentValues.put(COL_6,endTime);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
           // Toast.makeText( c,assetName+ "Asset Booked Successfully ",Toast.LENGTH_LONG ).show();
            return true;
    }

    public Cursor getData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }


  /*  public Cursor getDataEmp(String date)
    {

      //  SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
       // SharedPreferences.Editor editor=sharedPreferences.edit();
       // editor.putString("Name",editTextEmail.getText().toString());
       // editor.apply();

        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM student_table WHERE NAME='"+ name+"'",null);
        Cursor cursor=db.rawQuery("SELECT * FROM student_table where " + COL_4+ " = ?",
                new String[]{date});
        return cursor;
    } */

    public boolean updateData(String id,String name,String assetName,String selectDate,String startTime,String endTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,assetName);
        contentValues.put(COL_4,selectDate);
        contentValues.put(COL_5,startTime);
        contentValues.put(COL_6,endTime);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }



}
