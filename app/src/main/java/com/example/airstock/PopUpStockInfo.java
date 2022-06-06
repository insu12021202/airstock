package com.example.airstock;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PopUpStockInfo extends Activity {
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor, cursor2;
    String sql, sql2;
    String name, count, inDate;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_stockrack);
        listView = findViewById(R.id.stockedlistview);

        ListViewAdapter adapter = new ListViewAdapter();

        helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        Intent intent = getIntent();
        //창고 포지션 받기
        String position = intent.getStringExtra("position1");
        Log.d("포지션 값이 얼마?", position);

        sql = "SELECT * FROM warehouseDB WHERE positionIndex = "+ position +";";


        cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            name = cursor.getString(1);
            count = cursor.getString(2);

            sql2 = "SELECT * FROM contacts WHERE name = '" + name + "'";
            cursor2 = db.rawQuery(sql2, null);
            while (cursor2.moveToNext()){
                inDate = cursor2.getString(3);
            }


            adapter.addItemToList(name, count, inDate);
            Log.d("재고 이름", name+" "+count+" "+inDate);
        }
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public void mOnClose(View v){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //바깥 레이어 클릭시 닫히지 않게
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){ return false; }
        return true;
    }

    //백 버튼 기능 막기
    @Override
    public void onBackPressed() {
        return;
    }

}
