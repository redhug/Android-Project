package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Add_Book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__book);
    }
    public void buttonSave_onClick(View v) {

        EditText bookTitle = findViewById(R.id.editText);
        String value1 = bookTitle.getText().toString();
        TextView displayTV = findViewById(R.id.displayTV);
        bookTitle.setText(value1);

        if (value1.isEmpty()) {
            displayTV.setText("please enter title of the book");
        }

        EditText author = findViewById(R.id.editText1);
        String value2 = author.getText().toString();
        TextView displayTV1 = findViewById(R.id.displayTV);
        author.setText(value2);
        if (value2.isEmpty()) {
            displayTV.setText("please enter author name");
        }

        EditText isbn = findViewById(R.id.editText2);
        String value3 = isbn.getText().toString();
        int desiredValue = Integer.parseInt(value3);
        TextView displayTV2 = findViewById(R.id.displayTV);
        isbn.setText(value3);

        if (value3.isEmpty()) {
            displayTV.setText("please enter a number");
        }
        EditText edition = findViewById(R.id.editText3);
        String value4 = edition.getText().toString();
        TextView displayTV3 = findViewById(R.id.displayTV);
        edition.setText(value4);
        if (value4.isEmpty()) {
            displayTV.setText("please enter edition number");
        }
        EditText bookCondition = findViewById(R.id.editText4);
        String value5 = bookCondition.getText().toString();
        TextView displayTV4 = findViewById(R.id.displayTV);
        bookCondition.setText(value5);
        if (value5.isEmpty()) {
            displayTV.setText("please enter book condition");
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



