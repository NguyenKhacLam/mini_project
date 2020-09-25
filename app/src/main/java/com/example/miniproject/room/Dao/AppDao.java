package com.example.miniproject.room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniproject.modals.News;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    void insert(News... news);

    @Update
    void update(News... news);

    @Delete
    void delete(News... news);

    @Query("SELECT * FROM NEWS WHERE NEWS.saved = :saved")
    List<News> getNewsByType(boolean saved);
}
