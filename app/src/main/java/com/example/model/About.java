package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class About {


    private String title;
    @SerializedName("title")
    @Expose
    private String description;
    @SerializedName("description")
    @Expose
    private String urlToImage;
    @SerializedName("urlToImage")
    @Expose
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlToImage;
    }

    public void setUrl(String url) {
        this.urlToImage = url;
    }
}
