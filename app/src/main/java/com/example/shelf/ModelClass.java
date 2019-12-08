package com.example.shelf;

public class ModelClass {
//declaring private instance variables of type integers and string
    private int imageResource;
    private String title;
    private String author;
    private String edition;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
// Constructor with parameters of type integer and string
    public ModelClass(int imageResource, String title, String author, String edition, String image) {
        this.imageResource = imageResource;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.image = image;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }
}