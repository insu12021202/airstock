package com.example.airstock;

import android.content.Context;
import android.database.Cursor;
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
                " name TEXT, count INT, inDate TEXT, outDate TEXT, inMemo TEXT, outMemo TEXT, inPrice TEXT, outPrice TEXT," +
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
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    //----------------------------------------------------------------------

    Cursor readAllData(){
        String query = "SELECT * FROM contacts ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//    Cursor readAllWareHouseData(){
//        String query = "SELECT * FROM warehouseDB ";
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if(db != null){
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }

    public void deleteData(String name1){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM contacts WHERE name = '" + name1 +"';");
    }

    public void deleteIfZero(String name1){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM contacts WHERE name = '" + name1 + "'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getString(2).equals(String.valueOf(0))){
            deleteData(name1);
        }
    }

    public void changeCount(String name1, String count1){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE contacts SET count = '" + count1 + "'WHERE name = '" + name1 + "';";
        db.execSQL(sql);
    }

    public void deleteWHData(String name1){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM warehouseDB WHERE name = '" + name1 +"';");
    }

    public void changeWHCount(String name1, String count1){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE warehouseDB SET count = '" + count1 + "'WHERE name = '" + name1 + "';";
        db.execSQL(sql);
    }

    public void updateIsPositioned(String name1, String isPositioned1){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE contacts SET isPositioned = '" + isPositioned1 + "'WHERE name = '" + name1 + "';";
        db.execSQL(sql);
    }

//    public void updateWHIsPositioned(String name1, Integer isPositioned1){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String sql = "UPDATE warehouseDB SET isPositioned = '" + isPositioned1 + "'WHERE name = '" + name1 + "';";
//        db.execSQL(sql);
//    }

    public void updateOutDate(String name1, String outDate1){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE contacts SET outDate = '" + outDate1 + "'WHERE name = '" + name1 + "';";
        db.execSQL(sql);
    }

//    public void updateWHOutDate(String name1, String outDate1){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String sql = "UPDATE warehouseDB SET outDate = '" + outDate1 + "'WHERE name = '" + name1 + "';";
//        db.execSQL(sql);
//    }

    public void updateIOOutDate(String name1, String outDate1){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE inOutDB SET outDate = '" + outDate1 + "'WHERE name = '" + name1 + "';";
        db.execSQL(sql);
    }

}


