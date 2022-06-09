package com.example.airstock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WarehouseStockSpec extends PopUpStockInfo{

    EditText release_input;
    Button release_button;

    String id, name, count, inDate, outDate, inMemo, outMemo, inPrice, outPrice, receiveClient, isPositioned;
    String positionIndex;


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
                    TextView isPositionedText = (TextView) findViewById(R.id.isPositionedText2);
                    count = String.valueOf(stockCount-stockReleaseAmount);
                    countText.setText(count);
                    isPositionedText.setText(count);

                    DBHelper myDB = new DBHelper(WarehouseStockSpec.this);
                    myDB.changeWHCount(name, count);
                    myDB.changeCount(name, count);
                    myDB.updateIsPositioned(name, count);
                    myDB.updateOutDate(name, outDate);
                    myDB.updateIOOutDate(name, outDate);

                    Toast.makeText(getApplicationContext(), "출고되었습니다", Toast.LENGTH_SHORT).show();
                }
                else if (stockReleaseAmount == stockCount){
                    TextView countText = (TextView) findViewById(R.id.countText2);
                    TextView isPositionedText = (TextView) findViewById(R.id.isPositionedText2);
                    count = String.valueOf(stockCount-stockReleaseAmount);
                    countText.setText(count);
                    isPositionedText.setText(count);

                    DBHelper myDB = new DBHelper(WarehouseStockSpec.this);
                    myDB.deleteWHData(name);
                    myDB.changeCount(name, count);
                    myDB.updateIsPositioned(name, count);
                    myDB.updateOutDate(name, outDate);
                    myDB.updateIOOutDate(name, outDate);
                    myDB.deleteIfZero(name); //선반 위 재고가 모든 재고일 때 모두 출고 -> 예외 처리

                    Toast.makeText(getApplicationContext(), "출고되었습니다.", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "재고 수량보다 많이 출고할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                //어댑터 새로고침
            }
        });
    }


    void getAndSetIntentData() {
        // 현재 기기의 시간을 받아옴
        long tmpTime = System.currentTimeMillis();
        Date date = new Date(tmpTime);
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy/MM/dd");
        String currentTime = formatTime.format(date);

        //Getting Data from Intent
        id = getIntent().getStringExtra("id2");
        name = getIntent().getStringExtra("name2");
        count = getIntent().getStringExtra("count2");
        inDate = getIntent().getStringExtra("inDate2");
        outDate = getIntent().getStringExtra("outDate2");
        inMemo = getIntent().getStringExtra("inMemo2");
        outMemo = getIntent().getStringExtra("outMemo2");
        inPrice = getIntent().getStringExtra("inPrice2");
        outPrice = getIntent().getStringExtra("outPrice2");
        receiveClient = getIntent().getStringExtra("receiveClient2");
        isPositioned = getIntent().getStringExtra("isPositioned2");
        positionIndex = getIntent().getStringExtra("positionIndex");

        // 출고 날짜를 현재 날짜로 설정
        outDate = currentTime;

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
