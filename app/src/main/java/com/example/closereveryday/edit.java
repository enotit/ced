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

public class edit extends AppCompatActivity {
    public EditText nameid, describe, password;
    private LinearLayout mBackgroundLinearLayout;
    final String SAVED_TEXT = "saved_text";
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().hide();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nameid = (EditText) findViewById(R.id.nameid2);
        nameid.setText(MainActivity.name);

        password = (EditText) findViewById(R.id.khg2);
        password.setText(Password.passwordId);

        describe = (EditText) findViewById(R.id.khg3);
        describe.setText(Describe.text);
    }
    public void onMyButtonClick(View v) {
       saveText();
        Intent intent = new Intent(edit.this,Osnova.class);
        startActivity(intent);
    }
    void saveText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(edit.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", nameid.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.putString("describe", describe   .getText().toString());
        editor.apply();
    }
}