package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ConstraintLayout detailslayout;
    TextView title;
    ArrayList<String>details=new ArrayList<String>();
    ArrayList<String>author=new ArrayList<String>();
    ArrayList<String>edition=new ArrayList<String>();
    ArrayList<String>images=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title=findViewById(R.id.titleTV);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final List<ModelClass> modelClassList = new ArrayList<>();
        ParseQuery<ParseObject> books=ParseQuery.getQuery("Add_Book");
        books.whereEqualTo("useremail",MainActivity.email);
        books.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userbooks, ParseException e) {
                if(e==null)
                {
                    for(ParseObject book:userbooks)
                    {
                        details.add(book.getString("title"));
                        author.add(book.getString("author"));
                        edition.add(book.getString("edition"));
                        images.add(book.getString("image"));


                    }
                }
                System.out.println(details);
                for(int i=0;i<details.size();i++)
                {
                    modelClassList.add(new ModelClass((R.drawable.bookimage),details.get(i), author.get(i),edition.get(i),images.get(i)));
                }
                Adapter adapter = new Adapter(modelClassList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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

    public void onClickBook(View v){
        title=findViewById(R.id.titleTV);
        Intent bookInfo = new Intent(this, Book_Info_Activity.class);
        bookInfo.putExtra("bookname",title.getText().toString());
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