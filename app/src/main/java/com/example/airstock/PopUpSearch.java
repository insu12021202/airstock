package com.example.airstock;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PopUpSearch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_search);
    }
    public void searchInStock(View view){
        Drawable targetShape = getResources().getDrawable(R.color.purple_200);
        Intent intent = new Intent();
        ArrayList<String> positionIndexList = new ArrayList<>();

        // my_stock.xml 에서 입력한 재고의 이름 받아오기
        EditText editName;
        editName = (EditText) findViewById(R.id.editWantedStock);
        String wantedName = editName.getText().toString();
        String searchName;
        String searchIndex;

        // db 세팅
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database;
        try {
            database = helper.getReadableDatabase();
        } catch (SQLException ex){
            database = helper.getWritableDatabase();
        }
        Cursor cursor = database.rawQuery("select name, positionIndex from warehouseDB", null);

        // db에서 재고 이름 검색
        while(cursor.moveToNext()){
            searchName = cursor.getString(0);
            searchIndex = cursor.getString(1);
            if(wantedName.equals(searchName)){
                if(searchIndex.equals(Integer.toString(R.id.table1))){
                    positionIndexList.add(Integer.toString(R.id.table1));
                    Toast.makeText(getApplicationContext(), "찾으시는 재고는 1번 선반에 있습니다", Toast.LENGTH_SHORT).show();
                }
                if(searchIndex.equals(Integer.toString(R.id.table2))){
                    positionIndexList.add(Integer.toString(R.id.table2));
                    Toast.makeText(getApplicationContext(), "찾으시는 재고는 2번 선반에 있습니다", Toast.LENGTH_SHORT).show();
                }
                if(searchIndex.equals(Integer.toString(R.id.table3))){
                    positionIndexList.add(Integer.toString(R.id.table3));
                    Toast.makeText(getApplicationContext(), "찾으시는 재고는 3번 선반에 있습니다", Toast.LENGTH_SHORT).show();
                }
                if(searchIndex.equals(Integer.toString(R.id.table4))){
                    positionIndexList.add(Integer.toString(R.id.table4));
                    Toast.makeText(getApplicationContext(), "찾으시는 재고는 4번 선반에 있습니다", Toast.LENGTH_SHORT).show();
                }
                if(searchIndex.equals(Integer.toString(R.id.table5))){
                    positionIndexList.add(Integer.toString(R.id.table5));
                    Toast.makeText(getApplicationContext(), "찾으시는 재고는 5번 선반에 있습니다", Toast.LENGTH_SHORT).show();
                }
                if(searchIndex.equals(Integer.toString(R.id.table6))){
                    positionIndexList.add(Integer.toString(R.id.table6));
                    Toast.makeText(getApplicationContext(), "찾으시는 재고는 6번 선반에 있습니다", Toast.LENGTH_SHORT).show();
                }
            }
        }
        intent.putExtra("positionIndexList", positionIndexList);
        setResult(RESULT_OK, intent);
        finish();
    }
}
