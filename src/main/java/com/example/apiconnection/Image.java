package com.example.apiconnection;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("height")
    private int height;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("width")
    private int width;

    public int getHeight() {
        return height;
    }


    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }


    public int getWidth() {
        return width;
    }

}
