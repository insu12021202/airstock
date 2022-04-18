package com.example.airstock;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    Stock selectedStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedStock(); //선택한 재고정보 가져오기

        setValues(); // 가져온 정보 화면에 보여주기
    }

    private void setValues() {

        TextView tv = findViewById(R.id.stock_detail_name);
        ImageView iv = findViewById(R.id.stock_detail_image);

        tv.setText(selectedStock.getName());
        iv.setImageResource(selectedStock.getImage());
    }

    private void getSelectedStock() {

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        selectedStock = MainActivity.stockList.get(Integer.valueOf(id));
    }
}