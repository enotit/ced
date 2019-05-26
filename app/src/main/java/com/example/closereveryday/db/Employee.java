package com.example.closereveryday.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo
    public String why;

    @ColumnInfo
    public long summa;
}