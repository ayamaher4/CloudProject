package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class About {


    private String title;

    private String description;

    private String urlToImage;




    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getUrlImage() {
        return urlToImage;
    }


}
