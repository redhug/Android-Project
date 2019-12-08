package com.example.shelf;

import android.content.Intent;

import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;
// This class shows the personal information of the user
public class ProfileActivity extends AppCompatActivity {
    TextView contact;
    TextView username;
    TextView address;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        contact=findViewById(R.id.contact);
        username=findViewById(R.id.username);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        email.setText(MainActivity.email);
        ParseQuery<ParseUser> user=ParseUser.getQuery();
        user.whereEqualTo("email",MainActivity.email);
        user.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e==null)
                {
                    for(ParseObject usr:users)
                    {
                        contact.setText(usr.getString("Contact"));
                        username.setText(usr.getString("username"));
                        address.setText(usr.getString("Address"));
                    }
                }
            }
        });
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

    public void onLogout(View v){
        ParseUser.logOut();
        Toast.makeText(ProfileActivity.this,"Logging out", Toast.LENGTH_LONG).show();
        Intent tip_intent = new Intent(this, MainActivity.class);
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
}