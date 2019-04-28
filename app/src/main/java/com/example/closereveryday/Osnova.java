package com.example.closereveryday;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Osnova extends Activity {

    private TextView textFavorites;
    private TextView textCollection;
    private TextView textFriends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osnova);
        textFavorites = (TextView) findViewById(R.id.text_favorites);
        textCollection = (TextView) findViewById(R.id.text_collection);
        textFriends = (TextView) findViewById(R.id.text_friends);

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(getBottomNavigationListener());
    }
    @NonNull
    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_favorite:
                        textFavorites.setVisibility(View.VISIBLE);
                        textCollection.setVisibility(View.GONE);
                        textFriends.setVisibility(View.GONE);
                        break;

                    case R.id.action_collection:
                        textFavorites.setVisibility(View.GONE);
                        textCollection.setVisibility(View.VISIBLE);
                        textFriends.setVisibility(View.GONE);
                        break;

                    case R.id.action_friends:
                        textFavorites.setVisibility(View.GONE);
                        textCollection.setVisibility(View.GONE);
                        textFriends.setVisibility(View.VISIBLE);
                        break;

                }
                return true;
            }
        };
    }


}
