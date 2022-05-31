package com.example.airstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.receiveButton: // 입고 메뉴로 이동
                Intent intent1 = new Intent(this, ReceivingStocks.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "입고 화면", Toast.LENGTH_SHORT).show();
                break;

            case R.id.clearanceButton: // 재고정리 메뉴로 이동
                Intent intent3 = new Intent(this, ClearanceView.class);
                startActivity(intent3);
                Toast.makeText(getApplicationContext(), "재고 목록 화면", Toast.LENGTH_SHORT).show();
                break;

            case R.id.releaseButton: // 재고정리 메뉴로 이동
                Intent intent4 = new Intent(this, ReleasingStocks.class);
                startActivity(intent4);
                Toast.makeText(getApplicationContext(), "출고 화면", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myStockButton:
                Intent intent2 = new Intent(this, MyStockView.class);
                startActivity(intent2);
                Toast.makeText(getApplicationContext(), "내 창고", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    /*// 입고 메뉴로 이동
    public void moveToReceivingStocks(View view){
        //Toast.makeText(getApplicationContext(), "입고화면", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ReceivingStocks.class);
        //Toast.makeText(getApplicationContext(), "입고화면", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "입고화면", Toast.LENGTH_SHORT).show();
    }

    //재고정리 메뉴로 이동
    public void moveToClearance(View view){
        Intent intent = new Intent(this, ClearanceView.class);
        startActivity(intent);
    }*/
}