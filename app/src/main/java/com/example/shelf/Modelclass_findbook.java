package com.example.shelf;

public class Modelclass_findbook {


        private int imageResource;
        private String title;
        private String author;
        private String edition;
        private String useremail;


        public Modelclass_findbook(int imageResource, String title, String author, String edition,String useremail) {
            this.imageResource = imageResource;
            this.title = title;
            this.author = author;
            this.edition = edition;
            this.useremail=useremail;
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


