package com.example.miniproject.room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.miniproject.room.models.News;

@Dao
public interface AppDao {
    @Insert
    void insert(News... news);

    @Delete
    void delete(News... news);

    @Query("SELECT * FROM NEWS WHERE NEWS.saved = :saved")
    void getNewsByType(boolean saved);
}
