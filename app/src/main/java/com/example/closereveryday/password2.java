package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class password2 extends AppCompatActivity {
    private LinearLayout mBackgroundLinearLayout;
    private EditText passwd;
    public String password;
    final String SAVED_TEXT = "saved_text";
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password2);
        getSupportActionBar().hide();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        int parsedColor = Color.parseColor("#E4E4E4");
        mBackgroundLinearLayout.setBackgroundColor(parsedColor);
        passwd = (EditText) findViewById(R.id.khg);
    }

    public void onMyButtonCl(View view){
        password = passwd.getText().toString();
        String raz = Password.passwordId;
        if(password.length() == 0){}
            else{
        if(raz.length() < 3 ){
            Toast.makeText(this, " Пароль слишком маленький", Toast.LENGTH_SHORT).show();
        }else{
        if(password.equals(raz)){
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor pw = sPref.edit();
            pw.putString(SAVED_TEXT, raz);
            pw.commit();
            Intent intent = new Intent(this, Describe.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Вы ввели неверный пароль.", Toast.LENGTH_LONG).show();
        finish();
        }}}

    }
    void finishes(){
        finish();
    }
    }
