package com.example.miniproject.api;

import com.example.miniproject.modals.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("top-headlines")
    Call<NewsResponse> getNews(@Query("country") String country, @Query("apiKey") String apiKey);

    @GET("everything")
    Call<NewsResponse> getSearchedNews(@Query("q") String q, @Query("apiKey") String apiKey);
}
