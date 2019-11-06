package com.example.shelf;

public class ModelClass {

    private int imageResource;
    private String title;
    private String body;
    private String body2;

    public ModelClass(int imageResource, String title, String body, String body2) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
        this.body2 = body2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getBody2() {
        return body2;
    }
}
