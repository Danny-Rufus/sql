package com.example.paul.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PAUL on 29-03-2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "School";
    //tableName called SCHOOL is created

    public static final String _ID = "id";
    public static final String NAME = "name";
    public static final String LNAME = "lname";
    //field is created in table

    public static final String DB_NAME = "SC.DB"; //database name

    public static final int DB_VER = 1;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + LNAME + " TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }



}
