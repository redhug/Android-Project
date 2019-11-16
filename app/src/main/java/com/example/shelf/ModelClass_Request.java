package com.example.shelf;

public class ModelClass_Request {

    private String title;
    private String useremail;
    private String accept;
    private String reject;

    public ModelClass_Request(String title, String useremail) {
        this.title = title;
        this.useremail = useremail;
        //this.accept = accept;
        //this.reject = reject;
    }

    public String getTitle() {
        return title;
    }

    public String getUseremail() {
        return useremail;
    }
    /* public String getAccept() {
        return accept;
    }

    public String getReject() {
        return reject;
    }*/
}
