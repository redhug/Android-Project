package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class Activity_RequestRecieved extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView bookname;
    TextView username;
    Button accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__request_recieved);
        bookname=findViewById(R.id.booktitle);
        username=findViewById(R.id.user);
        accept=findViewById(R.id.accept);
        ParseQuery<ParseObject> req=ParseQuery.getQuery("request");
        req.whereEqualTo("recepientemial", MainActivity.email);
        req.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject user, ParseException e) {
                if(e==null) {
                    System.out.println(user.getString("senderemail"));
                    bookname.setText(user.getString("title"));
                    username.setText(user.getString("senderemail"));
                }
                else {
                    Log.d("Exception occured","e");
                }
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        List<ModelClass_Request> modelClassList_Request = new ArrayList<>();
        modelClassList_Request.add(new ModelClass_Request( "i hate love story", "Samanth","accept", "reject"));
        modelClassList_Request.add(new ModelClass_Request( "i hate love story", "Samanth","accept", "reject"));



        Adapter_Request Adapter_Request = new Adapter_Request(modelClassList_Request);
        recyclerView.setAdapter(Adapter_Request);
        Adapter_Request.notifyDataSetChanged();

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

    public void onClickMy_Request(View view) {
        Intent requests = new Intent(this, Activity_Request.class);
        startActivityForResult(requests, 1);
    }

    public void onaccept(View view) {
        ParseObject query1 = new ParseObject("Add_Book");
        query1.put("title",bookname);
        query1.put("senderemail",username);
        query1.put("useremail",MainActivity.email);
        query1.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Success
                    //dlg.dismiss();
                    //alertDisplayer("Book added successfully","");
                } else {
                    // Error
                }
            }
        });
        accept.setEnabled(false);
    }
}
