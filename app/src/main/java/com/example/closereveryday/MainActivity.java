package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etText = findViewById(R.id.nameid);
        findViewById(R.id.enter_name_button)
                .setOnClickListener((v) -> onMyButtonClick());
    }


       public void onMyButtonClick(){
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
        Boolean naming = sharedPreferences.getBoolean("answer", false);
        if(naming){
            Intent intent = new Intent(MainActivity.this, MainPassword.class);
            startActivity(intent);
            finish();
        }
   }}
