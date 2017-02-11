package com.nguessan.script;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by Nguessan on 12/19/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts( id integer primary key not null , " + "username text not null , password text not null , email text not null);";

    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }
    public void insertContacts(Contacts c){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "Select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COL_1,count);
        values.put(COL_2,c.getUsername());
        values.put(COL_3,c.getEmail());
        values.put(COL_4,c.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
        cursor.close();
    }
    public String searchPass (String username){

        db = this.getReadableDatabase();
        String query = "select username, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a, b;
        b = "not found";

        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(username)){

                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return b;
    }
    public String searchEmai (String username){

        db = this.getReadableDatabase();
        String query = "select username, email from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a, b;
        b = "not found";

        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(username)){

                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return b;
    }

    public void updatePassword(String username, String password) {
        db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_4, password);
        db.update(TABLE_NAME, cv, COL_2 + "=" + "'" + username + "'", new String[] {} );
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }


}
