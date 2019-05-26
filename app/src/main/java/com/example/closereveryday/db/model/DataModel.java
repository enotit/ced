package com.example.closereveryday.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by gleb on 11/15/17.
 */

@Entity
public class DataModel {

    @NonNull
    @PrimaryKey
    private Integer title;
    private String description;

    @NonNull
    public int getTitle() {
        return title;
    }

    public void setTitle(@NonNull int title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
