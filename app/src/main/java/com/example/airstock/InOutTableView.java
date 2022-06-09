package com.example.airstock;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InOutTableView extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    int isTableOn = 1;
    String selected;
    String[] spinnerItems = {"입고 순", "출고 순"};
    private TableLayout tableLayout;
    String sql, sql2, sql3;

    public void renderTable(String sql){
        helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        cursor = db.rawQuery(sql, null);

        tableLayout = (TableLayout)findViewById(R.id.tableLayout);

        TableRow tablerow0 = new TableRow(this);
        tablerow0.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f

        ));
        tablerow0.setMinimumHeight(30);

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
            TextView textView4 = new TextView(this);
            textView4.setText(cursor.getString(3));
            textView4.setGravity(Gravity.CENTER);
            textView4.setTextSize(14);
            textView4.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            tablerow.addView(textView4);
            tableLayout.addView(tablerow);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inout_table);

        sql = "select name,count,inDate,outDate from inOutDB order by inDate";
        sql2 = "select name,count,inDate,outDate from inOutDB order by outDate";
        //스피너 생성
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerItems
        );
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected = spinnerItems[position];
                //입고 순
                if(selected == "입고 순"){
                    if(isTableOn == 0) {
                        tableLayout.removeAllViewsInLayout();
                        isTableOn = 1;
                    }
                    renderTable(sql);
                }
                //출고 순
                else if(selected == "출고 순"){
                    tableLayout.removeAllViewsInLayout();
                    renderTable(sql2);
                    isTableOn = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                renderTable(sql);
            }
        });
    }
}
