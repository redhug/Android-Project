package com.example.shelf;

import android.content.Intent;

import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

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

    public void onClickFindBook(View v){
        Intent tip_intent = new Intent(this, Find_Book.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onClickAdd_Book(View v){
        Intent tip_intent = new Intent(this, Add_Book.class);
        startActivityForResult(tip_intent, 1);
    }
    public void onLogout(View v){
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
    public void onClickHome(View v){
        Intent tip_intent = new Intent(this, HomeActivity.class);
        startActivityForResult(tip_intent, 1);
    }

    public void onClickRequest_Book(View view) {
        Intent tip_intent = new Intent(this, Activity_Request.class);
        startActivityForResult(tip_intent, 1);
    }
}