package com.example.shelf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class Activity_RequestRecieved extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> senderemail = new ArrayList<String>();
    ArrayList<String> objectid = new ArrayList<String>();
    Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__request_recieved);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ParseQuery<ParseObject> req = ParseQuery.getQuery("request");
        req.whereEqualTo("recepientemial", MainActivity.email);
        req.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> requests, ParseException e) {
                if (e == null) {
                    for (ParseObject req : requests) {
                        title.add(req.getString("title"));
                        senderemail.add(req.getString("senderemail"));
                        objectid.add(req.getObjectId());
                    }
                } else {
                    //exception
                }
                List<ModelClass_Request> modelClassList_Request = new ArrayList<>();
                for (int i = 0; i < title.size(); i++) {
                    modelClassList_Request.add(new ModelClass_Request(title.get((i)), senderemail.get(i), objectid.get(i)));
                }
                Adapter_Request Adapter_Request = new Adapter_Request(modelClassList_Request);
                recyclerView.setAdapter(Adapter_Request);
                Adapter_Request.notifyDataSetChanged();
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
        switch (item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(this, HomeActivity.class);
                startActivityForResult(home, 1);
                return (true);
            case R.id.findBook:
                Intent findBook = new Intent(this, Find_Book.class);
                startActivityForResult(findBook, 1);
                return (true);
            case R.id.requests:
                Intent requests = new Intent(this, Activity_RequestRecieved.class);
                startActivityForResult(requests, 1);
                return (true);
            case R.id.addBook:
                Intent addBook = new Intent(this, Add_Book.class);
                startActivityForResult(addBook, 1);
                return (true);
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivityForResult(profile, 1);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent tipInt) {
        try {
            if (requestCode == 1) {
                if (resultCode == 0) {
                    Log.d("Success", "got result");
                }
            }
        } catch (Exception e) {
        }

    }
}