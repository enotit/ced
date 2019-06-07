package com.example.closereveryday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Reset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbros);
        getSupportActionBar().hide();
    }

    public void last(View v){
        finish();
    }

}
