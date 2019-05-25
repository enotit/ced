package com.example.closereveryday;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class vizitka extends AppCompatActivity {

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizitka);
        getSupportActionBar().hide();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(this, "Внимание. Музыка. ", Toast.LENGTH_SHORT).show();
        Button button = (Button) findViewById(R.id.button6);
        button.setText("<");
        mus();
    }

    public void VizOsn(View v) {
        stope();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stope();
    }


    public void mus(){
        mPlayer=MediaPlayer.create(this, R.raw.music);
        mPlayer.start();
    }

    public void stope(){
        finish();
        mPlayer.stop();
    }
}
