package com.example.closereveryday;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class describe extends AppCompatActivity {
    private LinearLayout mBackgroundLinearLayout;
    private EditText ev; private TextView evi; private int s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe);
        getSupportActionBar().hide();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        int parsedColor = Color.parseColor("#E4E4E4");
        mBackgroundLinearLayout.setBackgroundColor(parsedColor);
    }
    public void onMyButtonCl(View view){
        ev = findViewById(R.id.khg);
        evi = findViewById(R.id.textView);
        String text = ev.getText().toString();
        if(text.length() == 0){
            evi.setText("Уверены, что хотите не ставить описание?");

            if(s == 0){
                s = s + 1;
            } else{nextAct();}


        }else{
        }
    }
    public void nextAct(){
        Intent intent = new Intent(this, describe.class);
        startActivity(intent);
    }
}
