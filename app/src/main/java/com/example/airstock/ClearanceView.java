package com.example.airstock;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClearanceView extends AppCompatActivity {

    private ArrayList<ExampleData> items = new ArrayList<>();
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    String selected;
    String sql1, sql2;
    String[] spinnerItems = {"알파벳 순", "입고일 순"};
    boolean isTableOn = false;
    private TableLayout tableLayout;

    //입출고목록 버튼 onClick 메소드
    public void onClick(View view) {
        Intent intent = new Intent(this, InOutTableView.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "입출고 목록", Toast.LENGTH_SHORT).show();
    }

    public void renderTable(String sql){
        helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        cursor = db.rawQuery(sql, null);

        tableLayout = (TableLayout)findViewById(R.id.clearanceTable);
        //table 동적 생성
        while(cursor.moveToNext()){
            TableRow tablerow = new TableRow(this);
            tablerow.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            tablerow.setMinimumHeight(30);
            TextView textView = new TextView(this);
            textView.setText(cursor.getString(0));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            tablerow.addView(textView);
            TextView textView2 = new TextView(this);
            textView2.setText(cursor.getString(1));
            textView2.setGravity(Gravity.CENTER);
            textView2.setTextSize(14);
            textView2.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            tablerow.addView(textView2);
            TextView textView3 = new TextView(this);
            textView3.setText(cursor.getString(2));
            textView3.setGravity(Gravity.CENTER);
            textView3.setTextSize(14);
            textView3.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            tablerow.addView(textView3);
            tableLayout.addView(tablerow);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearance);
        sql1 = "select name,count,inDate from contacts order by name";
        sql2 = "select name,count,inDate from contacts order by inDate";

        //스피너 생성
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerItems
        );
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //알파벳 순, 입고일 순에 따라 목록 보여주는 순서 바꾸기
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected = spinnerItems[position];
                //알파벳 순
                if(selected == "알파벳 순"){
                    if(isTableOn) {
                        tableLayout.removeAllViewsInLayout();
                        isTableOn = false;
                    }
                    renderTable(sql1);
                }
                //입고일 순
                else {
                    tableLayout.removeAllViewsInLayout();
                    renderTable(sql2);
                    isTableOn = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                renderTable(sql1);
            }
        });
    }

}
