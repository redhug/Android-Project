package com.example.shelf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void onClickFindBook(View v){
        Intent tip_intent = new Intent(this, Find_Book.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onClickAdd_Book(View v){
        Intent tip_intent = new Intent(this, Add_Book.class);
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
    public void onClickHome(View v){
        Intent tip_intent = new Intent(this, MainActivity.class);
        startActivityForResult(tip_intent, 1);
    }

    public void onClickRequest_Book(View view) {
        Intent tip_intent = new Intent(this, Activity_Request.class);
        startActivityForResult(tip_intent, 1);
    }
}
