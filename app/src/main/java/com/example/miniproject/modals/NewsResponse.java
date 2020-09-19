package com.example.miniproject.modals;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResponse {
    @SerializedName("articles")
    private ArrayList<News> news;

    public ArrayList<News> getNews() {
        return news;
    }
}
