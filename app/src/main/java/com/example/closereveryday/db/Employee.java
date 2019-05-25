package com.example.closereveryday.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey
    public Integer id;

    public String why;

    public long summa;
}