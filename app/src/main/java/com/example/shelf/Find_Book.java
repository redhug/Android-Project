package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Find_Book extends AppCompatActivity {
    EditText title;
    TextView titlename;
    TextView authorname;
    TextView editionnumber;
    public static String useremail;
    public static String booktitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__book);
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
            case R.id.requests:
                Intent requests = new Intent(this, Activity_Request.class);
                startActivityForResult(requests, 1);
                return(true);
            case R.id.addBook:
                Intent addBook = new Intent(this, Add_Book.class);
                startActivityForResult(addBook, 1);
                return(true);
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivityForResult(profile, 1);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    public void onClickBook(View v){
        Intent ownerIntent = new Intent(this, UserActivity.class);
        startActivityForResult(ownerIntent, 1);
    }
    public void search(View view) {
        title=findViewById(R.id.search);
        titlename=findViewById(R.id.title);
        authorname=findViewById(R.id.author);
        editionnumber=findViewById(R.id.edition);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Add_Book");
        query.whereEqualTo("title", title.getText().toString().toUpperCase());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject book, ParseException e) {
                if (e == null) {
                    String title = book.getString("title");
                    String author =  book.getString("author");
                    String edition= book.getString("edition");
                    titlename.setText(title);
                    booktitle=title;
                    authorname.setText(author);
                    editionnumber.setText(edition);
                    useremail=book.getString("useremail");

                } else {
                    // Something is wrong
                }
            }
        });

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
