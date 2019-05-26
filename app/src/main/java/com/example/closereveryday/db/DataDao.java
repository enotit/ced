package com.example.closereveryday.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.closereveryday.db.model.DataModel;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insert(DataModel dataModel);

    @Delete
    void delete(DataModel dataModel);

    @Query("SELECT * FROM DataModel")
    List<DataModel> getAllData();
}
