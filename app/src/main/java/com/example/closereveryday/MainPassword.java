package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainPassword extends AppCompatActivity {

    private String ps, pasw, bl, jj;
    private LinearLayout mBackgroundLinearLayout;
    private EditText et;
    private int in;
    private long millis, mil, nin;
    MediaPlayer mPlayer;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mBackgroundLinearLayout = findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_password);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainPassword.this);
        String de = sharedPreferences.getString("describe", "");

        TextView tv = findViewById(R.id.textView4);
        if(de.equals("")) tv.setText("Описание отсутствует, измените в настройках.");
        else tv.setText(de);

        nin = sharedPreferences.getLong("block", 0);
        getDates();
        if(!(millis > nin)) {
            long ost = (nin - millis) / 60000;
            if(ost == 0) {
                Toast.makeText(this, "До окончания блокировки < минуты.", Toast.LENGTH_SHORT).show();
            } else {
                if(ost == 1) {
                    Toast.makeText(this, "Подождите ещё 1 минуту.", Toast.LENGTH_SHORT).show();
                }
                if(ost == 2 || ost == 3 || ost == 4) {
                    Toast.makeText(this, "Подождите ещё " + ost + " минуты.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "error time", Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        }

        findViewById(R.id.main_password_enter)
                .setOnClickListener((v) -> onMyButtonClick());
    }

    public void startMusicCancel(){
        mPlayer = MediaPlayer.create(this, R.raw.otkazka);
        mPlayer.start();
    }
    public void onMyButtonClick(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainPassword.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ps  = sharedPreferences.getString("password", "");
        et = findViewById(R.id.khg);
        pasw = et.getText().toString();
        tv = findViewById(R.id.textView4);

        nin = sharedPreferences.getLong("block", 0);
        getDates();
        if(pasw.equals(ps)) {
            Intent intent = new Intent(this, Base.class);
            finish();
            startActivity(intent);
        } else {
            startMusicCancel();
            in += 1;
            et.setText("");
            if(in == 1) Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
            if(in == 2) Toast.makeText(this, "Последняя попытка и блокировка на 5 минут.", Toast.LENGTH_SHORT).show();
            if(in == 3){
                getDates();
                Toast.makeText(this, "Приложение заблокировано.", Toast.LENGTH_LONG).show();
                millis += 300000;
                editor.putLong("block", millis);
                editor.apply();
                finish();
            }
        }
    }



    public void getDates(){
        Date date = new Date();
        millis = date.getTime();
    }

    public void Help(View v){
        Intent intent = new Intent(MainPassword.this, Reset.class);
        startActivity(intent);
    }

}