package com.example.closereveryday;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Osnova extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osnova);
        getSupportActionBar().hide();

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(getBottomNavigationListener());
    }

    @NonNull
    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_setting:
                        /* textFavorites.setVisibility(View.VISIBLE);
                        textCollection.setVisibility(View.GONE); */
                        break;

                    case R.id.action_aim:
                        Intent intent = new Intent(Osnova.this,SettingActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.action_money:

                        break;

                }
                return true;
            }
        };
    }
}