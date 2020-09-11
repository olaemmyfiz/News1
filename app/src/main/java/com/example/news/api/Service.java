package com.example.news.api;

import com.example.news.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/v2/everything?domains=techcrunch.com,thenextweb.com&apiKey=49ae22420a2442c98d1097d5fcb5894a")
    Call<ItemResponse> getItem();
    //Call<GistResponse> getNews();
}
