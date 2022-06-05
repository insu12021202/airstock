package com.example.airstock;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class PopUpInMyStock extends Activity {
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    String sql, sql2, sql3;
    String isPositioned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_mystock);
        helper = new DBHelper(this);

    }

    public void onPopUpClosed(View view){

        db = helper.getWritableDatabase();
        Intent intent = getIntent();
        //재고 아이디 받기
        String data = intent.getStringExtra("data");
        String position = intent.getStringExtra("position");
        //사용자가 입력한 재고 수량과 재고를 위치할 층수 받아오기
        EditText stockNum =  findViewById(R.id.stockNum);
        EditText stockFloor = findViewById(R.id.stockFloor);

        Log.d("PopUp", "재고이름: " + data + " 재고 수량: " + stockNum.getText() + " 재고 층수: " + stockFloor.getText());
        //warehouseDB table에 재고이름를 통해 수량 및 위치 정보 최신화 하기
        sql3 = "select isPositioned from contacts where name = '" + data + "'";
        cursor = db.rawQuery(sql3, null);
        while(cursor.moveToNext()){
            isPositioned = cursor.getString(0);
            Log.d("tagggg", isPositioned);
        }
        if(isPositioned.equals("")){
            sql = "UPDATE warehouseDB SET count='" + stockNum.getText() + "', positionIndex='"+ position + "', floorIndex='" + stockFloor.getText()+"' where name='" + data +"'";
            sql2 = "UPDATE contacts SET isPositioned= '" + stockNum.getText() +"' WHERE name= '"+ data +"'";
        }else{
            sql = "UPDATE warehouseDB SET count='" + String.valueOf(Integer.parseInt(isPositioned) + Integer.parseInt(stockNum.getText().toString())) + "', positionIndex='"+ position + "', floorIndex='" + stockFloor.getText()+"' where name='" + data +"'";
            sql2 = "UPDATE contacts SET isPositioned= '" + String.valueOf(Integer.parseInt(isPositioned) + Integer.parseInt(stockNum.getText().toString())) +"' WHERE name= '"+ data +"'";
        }

        db.execSQL(sql);
        db.execSQL(sql2);

        setResult(RESULT_OK, intent);
        finish();
    }

    //백 버튼 기능 막기
    @Override
    public void onBackPressed() {
        return;
    }
}
