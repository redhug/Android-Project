package com.example.shelf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetails extends AppCompatActivity {

    TextView titleTV,authorTV,editionTV;
    ImageView bookImage;
    String Title,Author,Edition;
    int ImageResourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Title = getIntent().getStringExtra("title");
        Author = getIntent().getStringExtra("author");
        Edition = getIntent().getStringExtra("edition");
        ImageResourse = getIntent().getIntExtra("imageView",1);
        titleTV = findViewById(R.id.titleTV);
        authorTV = findViewById(R.id.authorTV);
        editionTV = findViewById(R.id.editionTV);
        bookImage=findViewById(R.id.imageView);
        titleTV.setText(Title);
        authorTV.setText(Author);
        editionTV.setText(Edition);
        bookImage.setImageResource(ImageResourse);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(this, HomeActivity.class);
                startActivityForResult(home, 1);
                return(true);
            case R.id.findBook:
                Intent findBook = new Intent(this, Find_Book.class);
                startActivityForResult(findBook, 1);
                return(true);
            case R.id.requests:
                Intent requests = new Intent(this, Activity_RequestRecieved.class);
                startActivityForResult(requests, 1);
                return(true);
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivityForResult(profile, 1);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
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

}