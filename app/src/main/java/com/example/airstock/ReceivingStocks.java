package com.example.airstock;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


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
    }

    public void receiveListener(View view){
        Intent intent = new Intent(getApplicationContext(), ReceivingStocks.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "추가완료", Toast.LENGTH_SHORT).show();
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

        db.execSQL("INSERT INTO contacts VALUES (null, '" + name +"', '" + count +"', '" + inDate +"', '" + outDate +"', '" + inMemo +"', '" + outMemo +"', '" + inPrice +"'" +
                ", '" + outPrice +"', '" + receiveClient +"', '" + isPositioned +"');");
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

    public void search(View view){
        String name = editName.getText().toString();
        Cursor cursor;

        cursor = db.rawQuery("SELECT name, count, inDate, outDate, inMemo, outMemo, inPrice, outPrice, receiveClient, isPositioned FROM contacts WHERE name = '" + name + "';", null);

        while (cursor.moveToNext()){
            String count = cursor.getString(1);
            String inDate = cursor.getString(2);
            String outDate = cursor.getString(3);
            String inMemo = cursor.getString(4);
            String outMemo = cursor.getString(5);
            String inPrice = cursor.getString(6);
            String outPrice = cursor.getString(7);
            String receiveClient = cursor.getString(8);
            String isPositioned = cursor.getString(9);

            editCount.setText(count);
            editInDate.setText(inDate);
            editOutDate.setText(outDate);
            editInMemo.setText(inMemo);
            editOutMemo.setText(outMemo);
            editInPrice.setText(inPrice);
            editOutPrice.setText(outPrice);
            editReceiveClient.setText(receiveClient);
            editIsPositioned.setText(isPositioned);
        }
    }
}
