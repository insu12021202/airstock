package com.example.airstock;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ReceivingStocks extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText editName, editCount, editInDate, editOutDate, editInMemo, editOutMemo, editInPrice,
            editOutPrice, editReceiveClient, editIsPositioned;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recieve_stocks);
        helper = new DBHelper(this);

        try{
            db = helper.getWritableDatabase();
        } catch (SQLException ex){
            db = helper.getReadableDatabase();
        }

        editName = (EditText) findViewById(R.id.name);
        editCount = (EditText) findViewById(R.id.count);
        editInDate = (EditText) findViewById(R.id.inDate);
        editOutDate = (EditText) findViewById(R.id.outDate);
        editInMemo = (EditText) findViewById(R.id.inMemo);
        editOutMemo = (EditText) findViewById(R.id.outMemo);
        editInPrice = (EditText) findViewById(R.id.inPrice);
        editOutPrice = (EditText) findViewById(R.id.outPrice);
        editReceiveClient = (EditText) findViewById(R.id.receiveClient);
        editIsPositioned = (EditText) findViewById(R.id.isPositioned);


        long tmpTime = System.currentTimeMillis();
        Date date = new Date(tmpTime);
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy/MM/dd");
        String currentTime = formatTime.format(date);

        editInDate.setText(currentTime);
    }

    public void receiveListener(View view){
        Intent intent = new Intent(getApplicationContext(), ReceivingStocks.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "추가 완료", Toast.LENGTH_SHORT).show();
    }
    public void insert(View view){

        String name = editName.getText().toString();
        String count = editCount.getText().toString();
        String inDate = editInDate.getText().toString();
        String outDate = editOutDate.getText().toString();
        String inMemo = editInMemo.getText().toString();
        String outMemo = editOutMemo.getText().toString();
        String inPrice = editInPrice.getText().toString();
        String outPrice = editOutPrice.getText().toString();
        String receiveClient = editReceiveClient.getText().toString();
        String isPositioned = editIsPositioned.getText().toString();

        Toast.makeText(getApplicationContext(), "추가전", Toast.LENGTH_SHORT).show();

        db.execSQL("INSERT INTO contacts VALUES (null, '" + name +"', '" + count +"', '" + inDate +"', '" + outDate +"', '" + inMemo +"', '" + outMemo +"', '" + inPrice +"', '" + outPrice +"', '" + receiveClient +"', '" + isPositioned +"');");

        db.execSQL("INSERT INTO warehouseDB VALUES (null, '" + name +"', '" + count +"', '" + "" + "', '" +""+ "');");

        db.execSQL("INSERT INTO inOutDB VALUES (null, '" + name +"', '" + count +"', '" + inDate +"', '" + outDate +"', '"+ inDate +"');");
        Toast.makeText(getApplicationContext(), "추가완료", Toast.LENGTH_SHORT).show();

        editName.setText("");
        editCount.setText("");
        editInDate.setText("");
        editOutDate.setText("");
        editInMemo.setText("");
        editOutMemo.setText("");
        editInPrice.setText("");
        editOutPrice.setText("");
        editReceiveClient.setText("");
        editIsPositioned.setText("");
    }
}
