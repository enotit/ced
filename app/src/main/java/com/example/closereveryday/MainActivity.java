package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mBackgroundLinearLayout;
    public EditText etText;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etText = (EditText) findViewById(R.id.nameid);

        int parsedColor = Color.parseColor("#E4E4E4");
        mBackgroundLinearLayout.setBackgroundColor(parsedColor);
    }

    public void onMyButtonClick(View view){
        if (etText.length() == 1|| etText.length() == 0){
            Toast.makeText(this, "Введите имя или же Ник.", Toast.LENGTH_LONG).show();
        }
        else
        {
            name = etText.getText().toString();
            Intent intent = new Intent(MainActivity.this, Password.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String naming = sharedPreferences.getString("name", "");
        if(naming != ""){
            Intent intent = new Intent(MainActivity.this, Main_Password.class);
            startActivity(intent);
            finish();
        }
   }}
