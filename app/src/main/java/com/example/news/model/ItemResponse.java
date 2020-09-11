package com.example.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("articles")
    @Expose
    private List<Item> articles;

    public ItemResponse(List<Item> articles) {
        this.articles = articles;
    }

    public List<Item> getArticles() {
        return articles;
    }

    public void setArticles(List<Item> articles) {
        this.articles = articles;
    }
}
