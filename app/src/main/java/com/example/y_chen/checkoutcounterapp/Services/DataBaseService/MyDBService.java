package com.example.y_chen.checkoutcounterapp.Services.DataBaseService;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Y_Chen on 2017/11/13.
 */

public class MyDBService extends SQLiteOpenHelper {
    public MyDBService(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {super(context, "my.db", null, 1); }
    @Override
    //Called when it is first created
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE person(personid INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20))");

    }
    //Called when the software version number is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
    }

    public void save(Person p,MyDBService dbOpenHelper)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("INSERT INTO person(name,phone) values(?,?)",
                new String[]{p.getName(),p.getPhone()});
    }
}
