package com.example.closereveryday;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Password extends AppCompatActivity {
    public static EditText passwd;
    public static String passwordId;
    private LinearLayout mBackgroundLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        getSupportActionBar().hide();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        int parsedColor = Color.parseColor("#E4E4E4");
        mBackgroundLinearLayout.setBackgroundColor(parsedColor);

        passwd = (EditText) findViewById(R.id.khg);
    }
    public void onMyButtonClck(View view){
       passwordId = passwd.getText().toString();
       if(passwordId.length() >= 4){
        Intent intent = new Intent(this, password2.class);
        startActivity(intent);
       }
    }
}
