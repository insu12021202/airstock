package com.example.airstock;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Collection;

public class StockSpec extends ReleasingStocks {

    EditText release_input;
    Button release_button;

    String id, name, count, inDate, outDate, inMemo, outMemo, inPrice, outPrice, receiveClient, isPositioned;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_stocks);

        getAndSetIntentData();

        release_input = findViewById(R.id.releasecount);
        release_button = findViewById(R.id.release_button);
        release_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stockReleaseAmount = Integer.parseInt(release_input.getText().toString());
                int stockCount = Integer.parseInt(count);

                if (stockReleaseAmount < stockCount){
                    TextView countText = (TextView) findViewById(R.id.countText2);
                    count = String.valueOf(stockCount-stockReleaseAmount);
                    countText.setText(count);

                    DBHelper myDB = new DBHelper(StockSpec.this);
                    myDB.changeCount(name, count);

                    Toast.makeText(getApplicationContext(), "출고되었습니다", Toast.LENGTH_SHORT).show();
                }
                else if (stockReleaseAmount == stockCount){
                    TextView countText = (TextView) findViewById(R.id.countText2);
                    count = String.valueOf(stockCount-stockReleaseAmount);
                    countText.setText(count);

                    DBHelper myDB = new DBHelper(StockSpec.this);
                    myDB.deleteData(name);

                    Toast.makeText(getApplicationContext(), "출고되었습니다.", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "재고 수량보다 많이 출고할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                //어댑터 새로고침
                customAdapter.updateStockListItems(stockList);
            }
        });
    }


    void getAndSetIntentData() {
        //Getting Data from Intent
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        count = getIntent().getStringExtra("count");
        inDate = getIntent().getStringExtra("inDate");
        outDate = getIntent().getStringExtra("outDate");
        inMemo = getIntent().getStringExtra("inMemo");
        outMemo = getIntent().getStringExtra("outMemo");
        inPrice = getIntent().getStringExtra("inPrice");
        outPrice = getIntent().getStringExtra("outPrice");
        receiveClient = getIntent().getStringExtra("receiveClient");
        isPositioned = getIntent().getStringExtra("isPositioned");

        TextView nameText = (TextView) findViewById(R.id.nameText2);
        TextView countText = (TextView) findViewById(R.id.countText2);
        TextView inDateText = (TextView) findViewById(R.id.inDateText2);
        TextView outDateText = (TextView) findViewById(R.id.outDateText2);
        TextView inMemoText = (TextView) findViewById(R.id.inMemoText2);
        TextView outMemoText = (TextView) findViewById(R.id.outMemoText2);
        TextView inPriceText = (TextView) findViewById(R.id.inPriceText2);
        TextView outPriceText = (TextView) findViewById(R.id.outPriceText2);
        TextView receiveClientText = (TextView) findViewById(R.id.receiveClientText2);
        TextView isPositionedText = (TextView) findViewById(R.id.isPositionedText2);

        //Setting Intent Data
        nameText.setText(name);
        countText.setText(count);
        inDateText.setText(inDate);
        outDateText.setText(outDate);
        inMemoText.setText(inMemo);
        outMemoText.setText(outMemo);
        inPriceText.setText(inPrice);
        outPriceText.setText(outPrice);
        receiveClientText.setText(receiveClient);
        isPositionedText.setText(isPositioned);
    }


}
