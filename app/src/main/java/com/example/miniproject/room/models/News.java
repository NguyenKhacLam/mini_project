package com.example.miniproject.room.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private String image;
    @ColumnInfo
    private String pathFile;
    @ColumnInfo
    private String url;
    @ColumnInfo
    private boolean saved;

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getUrl() {
        return url;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getPathFile() {
        return pathFile;
    }
}
