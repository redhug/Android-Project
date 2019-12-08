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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class Find_Book extends AppCompatActivity {

    private RecyclerView recyclerView;
    EditText title;
    TextView titlename;
    TextView authorname;
    TextView editionnumber;
    TextView email;
    ArrayList<String>images=new ArrayList<String>();
    ArrayList<String> booktitle=new ArrayList<String>();
    ArrayList<String> author=new ArrayList<String>();
    ArrayList<String> edition=new ArrayList<String>();
    ArrayList<String> useremail=new ArrayList<String>();
    List<Modelclass_findbook> modelClassList_findbook = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__book);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

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
        Intent ownerIntent = new Intent(this, UserActivity.class);
        startActivityForResult(ownerIntent, 1);
    }
    // This method is used to search the book
    public void search(View view) {
        booktitle.clear();
        author.clear();
        edition.clear();
        useremail.clear();
        modelClassList_findbook.clear();
        title=findViewById(R.id.search);
        titlename=findViewById(R.id.titleTV);
        authorname=findViewById(R.id.author);
        editionnumber=findViewById(R.id.edition);
        email=findViewById(R.id.useremail);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Add_Book");
        query.whereEqualTo("title", title.getText().toString().toUpperCase());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> books, ParseException e) {
                if(e==null)
                {
                    for(ParseObject book:books )
                    {
                        String email=book.getString("useremail");
                        System.out.println(book);
                        if(!(email.equalsIgnoreCase(MainActivity.email)))
                        {
                            booktitle.add(book.getString("title"));
                            author.add(book.getString("author"));
                            edition.add(book.getString("edition"));
                            useremail.add(book.getString("useremail"));
                            images.add(book.getString("image"));
                        }
                    }
                }
                else
                {
                    Log.d("No Books",e.toString());
                }
                System.out.println(booktitle);
                find();
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
    // If no book is found, a toast message is displayed
    public void find(){

        if(booktitle.isEmpty())
        {
            Toast.makeText(this,"No books found",Toast.LENGTH_LONG).show();
        }
        for(int i=0;i<booktitle.size();i++)
        {
            modelClassList_findbook.add(new Modelclass_findbook((R.drawable.bookimage), booktitle.get(i), author.get(i),edition.get(i),useremail.get(i),images.get(i)));
        }
        Adaptor_findbook Adaptor_findbook = new Adaptor_findbook(modelClassList_findbook);
        recyclerView.setAdapter(Adaptor_findbook);
        Adaptor_findbook.notifyDataSetChanged();
    }
}