package com.example.shelf;

import android.widget.Button;

public class Model {

    private String text1;
    private String text2;


    public Model(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }



    public String getUsername() {
        return text1;
    }

    public String getBookname() {
        return text2;
    }


}