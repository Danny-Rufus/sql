package com.example.paul.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.paul.sql.DbHelper.TABLE_NAME;

/**
 * Created by PAUL on 29-03-2018.
 */

public class DBManager {
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase sql;

    public DBManager(Context context){
        this.context=context;
    }
    public DBManager open(boolean isReadable){
        dbHelper=new DbHelper(context);
        if (isReadable){
            sql=dbHelper.getReadableDatabase();
        }else {
            sql=dbHelper.getWritableDatabase();
        }
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public void insertSchool(String name, String lname){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbHelper.NAME,name);
        contentValues.put(DbHelper.LNAME,lname);
        sql.insert(TABLE_NAME,null,contentValues);
    }
    public void updateSchool(String id,String name,String lname){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbHelper._ID,id);
        contentValues.put(DbHelper.NAME,name);
        contentValues.put(DbHelper.LNAME,lname);
        sql.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ id });
    }
   public void deleteData(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
          db.delete(TABLE_NAME,"ID = ?",new String[]{id});
      //  db.execSQL("DELETE FROM" + TABLE_NAME + "WHERE" +_ID+ "=' "+ id +"'");
          db.close();

    }




    public Cursor getSchool(){
        String[] columns=new String[]{DbHelper._ID, DbHelper.NAME, DbHelper.LNAME};

        Cursor cursor=sql.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
