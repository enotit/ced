package com.example.closereveryday.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    private Integer title;
    @ColumnInfo
    private String description;


    @NonNull
    public Integer getTitle() {
        return title;
    }

    public void setTitle(@NonNull Integer title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
