package com.example.airstock;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyStockView extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    String sql = "select name,count,inDate from contacts order by name";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_stock);

        //table에 OnDragListener 세팅
        findViewById(R.id.table1).setOnDragListener(new DragListener());
        findViewById(R.id.table2).setOnDragListener(new DragListener());

        //table 동적 생성
        listView = findViewById(R.id.listView);
        displayList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView name = view.findViewById(R.id.item_name);
                view.setTag(name.getText().toString());
                ClipData.Item item = new ClipData.Item(
                        (CharSequence) view.getTag());

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(view.getTag().toString(),
                        mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);

                view.startDragAndDrop(data, // data to be dragged
                        shadowBuilder, // drag shadow
                        view,
                        0
                );
                view.setVisibility(View.INVISIBLE);
            }
        });

    }
    void displayList() {
        //DB 생성 및 cursor 이용
        helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        cursor = db.rawQuery(sql, null);

        ListViewAdapter adapter = new ListViewAdapter();
        while (cursor.moveToNext()) {
            adapter.addItemToList(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }

        listView.setAdapter(adapter);
    }
    class DragListener implements View.OnDragListener{
        Drawable normalShape = getResources().getDrawable(R.drawable.table_border);
        Drawable targetShape = getResources().getDrawable(R.color.purple_200);

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            //이벤트 시작
            switch (dragEvent.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    view.setBackground(targetShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.setBackground(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    Log.d("DragListener",  dragEvent.getClipData().getItemAt(0).getText() + "들어왔음");
                    //재고 수량과 층수를 입력할 팝업 창 띄우기
                    Intent intent = new Intent(MyStockView.this, PopUpInMyStock.class);
                    intent.putExtra("data", dragEvent.getClipData().getItemAt(0).getText());
                    startActivity(intent);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    displayList();
                    view.setBackground(normalShape);
                    break;
                default:
                    break;
            }
            return true;
        }
    }


}
