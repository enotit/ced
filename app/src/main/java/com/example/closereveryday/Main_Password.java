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
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class Main_Password extends AppCompatActivity {

    private String ps, pasw, bl, jj;
    private LinearLayout mBackgroundLinearLayout;
    private EditText et;
    private int in;
    private long millis, mil;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main__password);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Main_Password.this);
        String de = sharedPreferences.getString("describe", "");

        TextView tv = findViewById(R.id.textView4);
        tv.setText(de);


        ps  = sharedPreferences.getString("password", "");

        et = (EditText) findViewById(R.id.khg);

        String jj = sharedPreferences.getString("block", "");
        tv.setText(jj);
       /* long nin = Long.valueOf(jj);
        getDates();
        if(mil > nin){
            Toast.makeText(this, "Добро пожаловать", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ждите, время ещё тикает...", Toast.LENGTH_SHORT).show();
        finish();} */
    }
    public void onMyButtonClick(View view){
        pasw = et.getText().toString();
        tv = findViewById(R.id.textView4);

        if(ps.equals(pasw)){
            Intent intent = new Intent(Main_Password.this, Osnova.class);
            startActivity(intent); }
            else{
            Toast.makeText(this, "Неверный пароль.", Toast.LENGTH_SHORT).show();
            in += 1;
            et.setText("");
            if(in == 3) tv.setText("Последняя попытка и блокировка на 5 минут.");
            if (in == 3) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Main_Password.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                getDates();
                mil = millis + 300000;
                bl = String.valueOf(mil);
                editor.putString("block", bl);

                finish();
            }
            }

    }
    public void getDates(){
        Date date = new Date();
        millis = date.getTime();
    }

}
