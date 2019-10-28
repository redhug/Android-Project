package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Add_Book extends AppCompatActivity {

    EditText bookTitle;
    EditText author;
    EditText isbn;
    EditText edition;
    EditText bookCondition;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__book);
        bookTitle = findViewById(R.id.editText);
        author = findViewById(R.id.editText1);
        isbn = findViewById(R.id.editText2);
        edition = findViewById(R.id.editText3);
        bookCondition = findViewById(R.id.editText4);
        submit = findViewById(R.id.button5);
    }
    public void buttonSave_onClick(View v) {
        final String btitle=bookTitle.getText().toString();
        final String bisbn=isbn.getText().toString();
        final String bauthor=author.getText().toString();
        final String bedition=edition.getText().toString();
        final String bcondition=bookCondition.getText().toString();



        if(btitle.length()==0){
            bookTitle.requestFocus();
            bookTitle.setError("cannot be empty!!");
        }
        else if(!btitle.matches("[a-zA-Z ]+"))
        {
            bookTitle.requestFocus();
            bookTitle.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        }





        if(bauthor.length()==0){
            author.requestFocus();
            author.setError("cannot be empty!!");
        }
        else if(!bauthor.matches("[a-zA-Z ]+"))
        {
            author.requestFocus();
            author.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        }


        if(bisbn.length()==0){
            isbn.requestFocus();
            isbn.setError("cannot be empty!!");
        }
        else if (!Pattern.matches("[0-20]+",bisbn)){
            isbn.requestFocus();
           // isbn.setError("isbn field should contain only numerical values");
        }



        if(bedition.length()==0){
            edition.requestFocus();
            edition.setError("cannot be empty!!");
         // edition.setError("edition field should contain only numerical values");

        }
        else if (!Pattern.matches("[0-20]+",bedition)){
            edition.requestFocus();
        }


        if(bcondition.length()==0){
            bookCondition.requestFocus();
            bookCondition.setError("cannot be empty!!");
        }
        else if(!bcondition.matches("[a-zA-Z ]+"))
        {
            bookCondition.requestFocus();
            bookCondition.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        }



//        if(btitle.length()==0){
//            bookTitle.requestFocus();
//            bookTitle.setError("Name field cannot be empty!!");
//        }
//        else if(bauthor.length()==0){
//            author.requestFocus();
//            author.setError("Name field cannot be empty!!");
//        }
//        else if(bisbn.length()==0){
//            isbn.requestFocus();
//            isbn.setError(" field cannot be empty!!");
//        }
//        else if(bedition.length()==0){
//            edition.requestFocus();
//            edition.setError(" field cannot be empty!!");
//        }
//        else if(bcondition.length()==0){
//            bookCondition.requestFocus();
//            bookCondition.setError("Name field cannot be empty!!");
//        }
//

        else {
            try {
                Intent toOtherIntent = new Intent(this, Add_Book.class);
                startActivity(toOtherIntent);
            } catch (Exception e) {
            }
        }



    }
        public void onClickFindBook(View v){
        Intent tip_intent = new Intent(this, Find_Book.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onClickHome(View v){
        Intent tip_intent = new Intent(this, MainActivity.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onClickProfile(View v){
        Intent tip_intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onClickRequests(View v){
        Intent tip_intent = new Intent(this, Add_Book.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onActivityResult(int requestCode,int resultCode,Intent tipInt) {
        try {
            if (requestCode == 1) {
                if (resultCode == 0) {
                    Log.d("Success", "got result");
                }
            }
        }
        catch (Exception e){
        }
    }

    public void onClickRequest_Book(View view) {
        Intent tip_intent = new Intent(this, Activity_Request.class);
        startActivityForResult(tip_intent, 1);
    }

    }



