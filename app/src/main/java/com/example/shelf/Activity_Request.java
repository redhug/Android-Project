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


public class Activity_Request extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__request);

        recyclerView = findViewById(R.id.recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        List<Model>mymodel = new ArrayList<>();
        mymodel.add(new Model("sushma", "java"));
        mymodel.add(new Model(" sushma", "java2"));
        mymodel.add(new Model( "sushma", "Adb"));
        mymodel.add(new Model(" sushma", "ADB2"));
        mymodel.add(new Model( "sushma", "web Apps"));
        mymodel.add(new Model(" sushma", "uxd"));
        mymodel.add(new Model( "sushma", "ML"));
        mymodel.add(new Model(" sushma", "Bigdata"));
        mymodel.add(new Model( "sushma", "English"));
        mymodel.add(new Model(" sushma", "Android"));

        adapter = new BookAdapter(mymodel);
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
            case R.id.home:
                Intent home = new Intent(this, HomeActivity.class);
                startActivityForResult(home, 1);
                return(true);
            case R.id.findBook:
                Intent findBook = new Intent(this, Find_Book.class);
                startActivityForResult(findBook, 1);
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

    public void onClickRequestRecieved(View view) {
        Intent requestsRecieved = new Intent(this, Activity_RequestRecieved.class);
        startActivityForResult(requestsRecieved, 1);
    }
}
