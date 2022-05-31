package com.example.airstock;

import static android.content.ContentValues.TAG;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReleasingStocks extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;
//    ArrayList<String> stock_id, stock_name, stock_count, stock_inDate, stock_outDate, stock_inMemo,
//            stock_outMemo, stock_inPrice, stock_outPrice, stock_receiveClient, stock_isPositioned;
    ArrayList<StockList> stockList = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_stocks);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);


        myDB = new DBHelper(ReleasingStocks.this);
//        stock_id = new ArrayList<>();
//        stock_name = new ArrayList<>();
//        stock_count = new ArrayList<>();
//        stock_inDate = new ArrayList<>();
//        stock_outDate = new ArrayList<>();
//        stock_inMemo = new ArrayList<>();
//        stock_outMemo = new ArrayList<>();
//        stock_inPrice = new ArrayList<>();
//        stock_outPrice = new ArrayList<>();
//        stock_receiveClient = new ArrayList<>();
//        stock_isPositioned = new ArrayList<>();

        setSearchView();
        storeDataInArray();

        customAdapter = new CustomAdapter(ReleasingStocks.this, this, stockList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReleasingStocks.this));

    }

    //----------------------------------------------------------------------------------

//    void storeDataInArrays() {
//        Cursor cursor = myDB.readAllData();
//        if (cursor.getCount() == 0) {
//            empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
//        } else {
//            while (cursor.moveToNext()) {
//                stock_id.add(cursor.getString(0));
//                stock_name.add(cursor.getString(1));
//                stock_count.add(cursor.getString(2));
//                stock_inDate.add(cursor.getString(3));
//                stock_outDate.add(cursor.getString(4));
//                stock_inMemo.add(cursor.getString(5));
//                stock_outMemo.add(cursor.getString(6));
//                stock_inPrice.add(cursor.getString(7));
//                stock_outPrice.add(cursor.getString(8));
//                stock_receiveClient.add(cursor.getString(9));
//                stock_isPositioned.add(cursor.getString(10));
//
//            }
//            empty_imageview.setVisibility(View.GONE);
//            no_data.setVisibility(View.GONE);
//        }
//    }

    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                stockList.add(new StockList(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10)
                ));
            }
        }
    }

    public void setSearchView()
    {
        SearchView searchView = findViewById(R.id.stock_search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.onActionViewExpanded();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    //----------------------------------------------------------------------------------

}



