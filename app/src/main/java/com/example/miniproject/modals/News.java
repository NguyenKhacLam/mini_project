package com.example.miniproject.modals;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class News {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("author")
    private String author;

    @ColumnInfo
    @SerializedName("title")
    private String title;

    @ColumnInfo
    @SerializedName("description")
    private String description;

    @ColumnInfo
    @SerializedName("url")
    private String url;

    @ColumnInfo
    @SerializedName("urlToImage")
    private String urlToImage;

    @ColumnInfo
    @SerializedName("publishedAt")
    private String publishedAt;

    @ColumnInfo
    private String pathFile;

    @ColumnInfo
    private boolean saved;

    public News() {
    }

    public News(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public int getId() {
        return id;
    }

    public String getPathFile() {
        return pathFile;
    }

    public boolean isSaved() {
        return saved;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
