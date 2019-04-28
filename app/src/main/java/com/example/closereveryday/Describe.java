package com.example.closereveryday;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
public class Describe extends AppCompatActivity {
    private LinearLayout mBackgroundLinearLayout;
    private EditText ev; private TextView evi; private int s = 0;
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe);
        getSupportActionBar().hide();

        mBackgroundLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        int parsedColor = Color.parseColor("#E4E4E4");
        mBackgroundLinearLayout.setBackgroundColor(parsedColor);
    }
    public void onMyButtonCl(View view){
        ev = findViewById(R.id.khg);
        evi = findViewById(R.id.textView);
        text = ev.getText().toString();
        if(text.length() == 0){
            evi.setText("Уверены, что хотите не ставить описание?");

            if(s == 0){
                s = s + 1;
            } else{dialogs();}


        }else{ dialogs();
        }


    }

    public void dialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Describe.this);
        builder.setTitle("Подтвердите ввод регистрации")
                .setMessage("Ваши данные: \n Ник: " + MainActivity.name + "\n Пароль: " + Password.passwordId + "\n Описание: " + text)
                .setCancelable(false)
                .setNegativeButton("Подтверждаю",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                saveText();
                                nextAct();
                            }
                        }
                )
                .setPositiveButton("Изменить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                edits();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void edits(){
        Intent intent = new Intent(this, edit.class);
        startActivity(intent);
    }
    public void nextAct(){
        Intent intent = new Intent(this, Osnova.class);
        startActivity(intent);
    }

    void saveText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Describe.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", MainActivity.name);
        editor.putString("password", Password.passwordId);
        editor.putString("describe", text);
        editor.apply();
    }
}