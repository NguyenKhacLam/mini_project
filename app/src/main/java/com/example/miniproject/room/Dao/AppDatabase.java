package com.example.miniproject.room.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.miniproject.modals.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db.news")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public abstract AppDao getAppDao();
}
