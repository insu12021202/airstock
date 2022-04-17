package com.example.airstock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public DbHelper(Context context){
        super(context, "airstockdb", null, DATABASE_VERSION);
    }

    //최초에 한 번만 실행
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //데이터베이스 구체화 하고 짜기
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
