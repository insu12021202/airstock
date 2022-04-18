package com.example.airstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Stock> stockList = new ArrayList<Stock>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpSearch();

        setUpData(); //데이터 셋팅

        setUpList(); //리스트 셋팅

        setUpOnClickListener();//상세페이지 이벤트

    }

    private void setUpSearch(){

        SearchView searchView = findViewById(R.id.stock_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Stock> filterStock = new ArrayList<>();

                for(int i = 0; i < stockList.size(); i++){

                    Stock stock = stockList.get(i);

                    //데이터와 비교해서 내가 쓴 재고 이름이 있다면
                    if(stock.getName().toLowerCase().contains(newText.toLowerCase())){

                        filterStock.add(stock);
                    }
                }

                StockAdapter adapter = new StockAdapter(getApplicationContext(), 0, filterStock);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    /**
     * 데이터 셋팅
     */
    private void setUpData() {

        Stock chicory = new Stock("0", "chicory", R.drawable.chicory);
        stockList.add(chicory);

        Stock cocacola = new Stock("1", "cocacola", R.drawable.cocacola);
        stockList.add(cocacola);

        Stock mayo = new Stock("2", "mayo", R.drawable.mayo);
        stockList.add(mayo);

        Stock noodle = new Stock("3", "noodle", R.drawable.noodle);
        stockList.add(noodle);

        Stock spinash = new Stock("4", "spinash", R.drawable.spinash);
        stockList.add(spinash);

        Stock chicory2 = new Stock("5", "chicory2", R.drawable.chicory);
        stockList.add(chicory2);

        Stock cocacola2 = new Stock("6", "cocacola2", R.drawable.cocacola);
        stockList.add(cocacola2);

        Stock mayo2 = new Stock("7", "mayo2", R.drawable.mayo);
        stockList.add(mayo2);

        Stock noodle2 = new Stock("8", "noodle2", R.drawable.noodle);
        stockList.add(noodle2);

        Stock spinash2 = new Stock("9", "spinash2", R.drawable.spinash);
        stockList.add(spinash2);

    }

    /**
     * 리스트 셋팅
     */
    private void setUpList() {

        listView = findViewById(R.id.stock_listView);

        StockAdapter adapter = new StockAdapter(getApplicationContext(), 0, stockList);
        listView.setAdapter(adapter);
    }


    /**
     * 상세페이지 이벤트
     */
    private void setUpOnClickListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Stock selectStock = (Stock) listView.getItemAtPosition(position);
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectStock.getId());
                startActivity(showDetail);
            }
        });
    }

}