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

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","2"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","3"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","4"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","5"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","6"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","7"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","8"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","9"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","10"));
        modelClassList.add(new ModelClass(R.drawable.bookimage, "i hate love story", "Samanth","11"));

        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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

    public void onClickBook(View v){
        Intent bookInfo = new Intent(this, Book_Info_Activity.class);
        startActivityForResult(bookInfo, 1);
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
