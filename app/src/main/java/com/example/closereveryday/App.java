package com.example.closereveryday;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.closereveryday.db.DatabaseHelper;



public class App extends Application {

    private static App instance;
    private static DatabaseHelper db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();

    }

    public static DatabaseHelper getDatabaseInstance() {
        return db;
    }
}
