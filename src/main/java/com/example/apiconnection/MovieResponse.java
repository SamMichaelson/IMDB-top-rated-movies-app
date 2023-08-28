package com.example.apiconnection;
//import com.example.apiconnection.Image;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("year")
    private int year;

    @SerializedName("image")
    private Image image;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Image getImage() {
        return image;
    }
}
