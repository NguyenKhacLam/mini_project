package com.example.miniproject.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private static Api api;
    public static Api getInstance(){
        if (api == null){
            api = new Retrofit.Builder()
                    .baseUrl("http://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api.class);
        }
        return api;
    }
}
