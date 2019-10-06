package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Add_Book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__book);
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
