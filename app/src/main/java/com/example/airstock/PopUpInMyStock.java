package com.example.airstock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PopUpInMyStock extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_mystock);

    }

    public void onPopUpClosed(View view){
        Intent intent = getIntent();
        //재고 아이디 받기
        String data = intent.getStringExtra("data");
        //사용자가 입력한 재고 수량과 재고를 위치할 층수 받아오기
        EditText stockNum =  findViewById(R.id.stockNum);

        EditText stockFloor = findViewById(R.id.stockFloor);

        Log.d("PopUp", "재고이름: " + data + " 재고 수량: " + stockNum.getText() + " 재고 층수: " + stockFloor.getText());
        //여기서 DB table에 재고 아이디를 통해 수량 및 위치 정보 최신화 하기
        setResult(RESULT_OK, intent);

        finish();
    }

    //백 버튼 기능 막기
    @Override
    public void onBackPressed() {
        return;
    }
}
