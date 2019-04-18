package com.example.closereveryday;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mBackgroundLinearLayout;
    private EditText etText;
    SharedPreferences sPref;
    public static String name;
    final String SAVED_TEXT = "saved_text";

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
        saveText();
        if (etText.length() == 1|| etText.length() == 0){
            Toast.makeText(this, "Введите имя или же Ник.", Toast.LENGTH_LONG).show();
        }
        else
        {
            name = etText.getText().toString();
            Intent intent = new Intent(MainActivity.this, Password.class);
            startActivity(intent);
        }
    }

    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.commit();
    }
}
