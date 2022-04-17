package com.example.airstock;

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

import java.util.ArrayList;

public class ClearanceView extends AppCompatActivity {

    private ArrayList<ExampleData> items = new ArrayList<>();


    String selected;
    String[] spinnerItems = {"알파벳 순", "입고일 순"};
    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearance);

//        테이블 row 동적 생성
        tableLayout = (TableLayout)findViewById(R.id.clearanceTable);
        items.add(new ExampleData("치커리", "23", "2022-03-03"));
        items.add(new ExampleData("바나나", "13", "2022-03-04"));
        items.add(new ExampleData("사과", "3", "2022-03-01"));
        items.add(new ExampleData("체리", "33", "2022-02-03"));
        items.add(new ExampleData("체리", "33", "2022-02-03"));
        items.add(new ExampleData("체리", "33", "2022-02-03"));
        items.add(new ExampleData("체리", "33", "2022-02-03"));
        items.add(new ExampleData("체리", "33", "2022-02-03"));


        for(int i = 0; i < items.size(); i++) {
            TableRow tablerow = new TableRow(this);
            tablerow.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            tablerow.setMinimumHeight(30);

                TextView textView = new TextView(this);
                textView.setText(items.get(i).name);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(14);
                textView.setLayoutParams(new TableRow.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1f
                        ));
                tablerow.addView(textView);
            TextView textView2 = new TextView(this);
            textView2.setText(items.get(i).count);
            textView2.setGravity(Gravity.CENTER);
            textView2.setTextSize(14);
            textView2.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            tablerow.addView(textView2);
            TextView textView3 = new TextView(this);
            textView3.setText(items.get(i).ReceiveDate);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
