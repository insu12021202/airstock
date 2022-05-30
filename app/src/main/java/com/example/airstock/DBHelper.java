package com.example.airstock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    // Database name
    private static final String DATABASE_NAME = "receive.db";

    // Database version
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override   // 데이터베이스와 초기 데이터 생성
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, count TEXT, inDate TEXT, outDate TEXT, inMemo TEXT, outMemo TEXT, inPrice TEXT, outPrice TEXT," +
                " receiveClinet TEXT, isPositioned TEXT )");
        db.execSQL("CREATE TABLE warehouseDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, count TEXT, positionIndex TEXT, floorIndex TEXT)" );
        db.execSQL("CREATE TABLE inOutDB (_ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "" +
                " name TEXT, count TEXT, inDate TEXT, outDate TEXT, inOutDate TEXT)");
        Log.v("Database create!! ", "success");
    }

    @Override   // 데이터베이스 업그레이드
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS defaultDB");
        db.execSQL("DROP TABLE IF EXISTS warehouseDB");
        db.execSQL("DROP TABLE IF EXISTS inOutDB");
        onCreate(db);
    }
}
