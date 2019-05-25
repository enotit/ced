package com.example.closereveryday;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class test_passsword extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_passsword);
        getSupportActionBar().hide();
        et = findViewById(R.id.khg);
    }
    public void onMyButtonClick(View v) {
        if(et.getText().toString() == ""){
            Toast.makeText(this, "Введите ваш пароль.", Toast.LENGTH_SHORT).show();
        }else{
            if(Osnova.passwords.equals(et.getText().toString())){
                saveText();
                Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                finish();
            }
        }

    }

    public  void saveText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(test_passsword.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", Osnova.nameid.getText().toString());
        editor.putString("password", Osnova.password.getText().toString());
        editor.putString("describe", Osnova.describe.getText().toString());
        editor.apply();
    }

}
