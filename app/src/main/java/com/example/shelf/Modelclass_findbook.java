package com.example.shelf;

public class Modelclass_findbook {

//declaring private instance variables of type int and string
    private int imageResource;
    private String title;
    private String author;
    private String edition;
    private String useremail;
    private String image;

// A constructor with parameters of type int and string
    public Modelclass_findbook(int imageResource, String title, String author, String edition,String useremail,String image) {
        this.imageResource = imageResource;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.useremail=useremail;
        this.image = image;
    }
    public String getImage() {
        return image;
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

    public String getUseremail() {
        return useremail;
    }

}

