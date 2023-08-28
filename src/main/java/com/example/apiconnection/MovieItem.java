package com.example.apiconnection;
public class MovieItem {

    private String title;
    private int year;
    private Image image;

    // Constructor, getters, setters, etc.

    public MovieItem(String title, int year, Image image) {
        this.title = title;
        this.year=year;
        this.image=image;
    }

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

