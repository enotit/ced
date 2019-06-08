package com.example.closereveryday;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Card extends AppCompatActivity {
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        getSupportActionBar().hide();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(this, "Внимание. Музыка. ", Toast.LENGTH_SHORT).show();
        Button button = findViewById(R.id.button6);
        button.setText("<");
        startMusic();
    }

    public void VizOsn(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        mPlayer.stop();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayer.stop();
    }

    public void startMusic() {
        mPlayer = MediaPlayer.create(this, R.raw.music);
        mPlayer.start();
    }
}
