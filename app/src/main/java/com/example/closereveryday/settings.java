package com.example.closereveryday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    public static EditText nameid, describe, password;
    public static String names, describes, passwords, np, dp, pp;
    final String SAVED_TEXT = "saved_text";
    public Boolean all, mus;
    private CheckBox musicCheckBox;
    MediaPlayer minusmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button button = findViewById(R.id.cancel_but);
        getSupportActionBar().hide();
        button.setText("сохранить и выйти");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(settings.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        names = sharedPreferences.getString("name","");
        nameid = (EditText) findViewById(R.id.nameid4);
        nameid.setText(names);

        passwords = sharedPreferences.getString("password", "");
        password = (EditText) findViewById(R.id.khg6);
        password.setText(passwords);

        describes = sharedPreferences.getString("describe", "");
        describe = (EditText) findViewById(R.id.khg7);
        describe.setText(describes);

        mus = sharedPreferences.getBoolean("musBo",true);

        musicCheckBox = (CheckBox)findViewById(R.id.musicid);

    }
    public void onMyButtonClick(View v) {
        all = true;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(settings.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        names = sharedPreferences.getString("name","");
        passwords = sharedPreferences.getString("password", "");
        names = sharedPreferences.getString("name","");
        np = nameid.getText().toString();
        dp = password.getText().toString();
        pp = describe.getText().toString();
        if(!names.equals(np) || !describes.equals(pp) || !passwords.equals(dp)) {
            all = false;
        }

        if(all) {
            Toast.makeText(this, "Вы ничего не изменили.", Toast.LENGTH_SHORT).show();
            startMusicCancel();
        } else {
            Intent intent = new Intent(settings.this, TestPassword.class);
            startActivity(intent);
        }
    }
    public  void startMusicCancel(){
        if(mus){
        minusmoney = MediaPlayer.create(this, R.raw.nodata);
        minusmoney.start();}
    }
    public void finishClick(View v){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(settings.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(musicCheckBox.isChecked()){
            editor.putBoolean("musBo",true);
        }else{
            editor.putBoolean("musBo",false);
        }
        editor.apply();

        finish();
    }
}
