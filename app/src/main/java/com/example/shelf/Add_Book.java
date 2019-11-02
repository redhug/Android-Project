package com.example.shelf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.parse.ParseObject;
import java.util.regex.Pattern;

public class Add_Book extends AppCompatActivity {

    EditText bookTitle;
    EditText author;
    EditText isbn;
    EditText edition;
    EditText bookCondition;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__book);
        bookTitle = findViewById(R.id.editText);
        author = findViewById(R.id.editText1);
        isbn = findViewById(R.id.editText2);
        edition = findViewById(R.id.editText3);
        bookCondition = findViewById(R.id.editText4);
        submit = findViewById(R.id.button5);
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
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivityForResult(profile, 1);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    public void buttonSave_onClick(View v) {
        final String btitle=bookTitle.getText().toString();
        final String bisbn=isbn.getText().toString();
        final String bauthor=author.getText().toString();
        final String bedition=edition.getText().toString();
        final String bcondition=bookCondition.getText().toString();
        boolean errors=false;
        if(btitle.length()==0){
            bookTitle.requestFocus();
            bookTitle.setError("cannot be empty!!");
            errors=true;
        }
        else if(!btitle.matches("[a-zA-Z ]+"))
        {
            bookTitle.requestFocus();
            bookTitle.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            errors=true;
        }
        if(bauthor.length()==0){
            author.requestFocus();
            author.setError("cannot be empty!!");
            errors=true;
        }
        else if(!bauthor.matches("[a-zA-Z ]+"))
        {
            author.requestFocus();
            author.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            errors=true;
        }
        if(bisbn.length()==0){
            isbn.requestFocus();
            isbn.setError("cannot be empty!!");
            errors=true;
        }
        else if (!Pattern.matches("[0-20]+",bisbn)){
            isbn.requestFocus();
            errors=true;
            isbn.setError("isbn field should contain only numerical values");
        }
        if(bedition.length()==0){
            edition.requestFocus();
            edition.setError("cannot be empty!!");
            errors=true;
        }
        else if (!Pattern.matches("[0-20]+",bedition)){
            edition.requestFocus();
            errors=true;
            isbn.setError("bedition field should contain only numerical values");
        }
        if(bcondition.length()==0){
            bookCondition.requestFocus();
            bookCondition.setError("cannot be empty!!");
            errors=true;
        }
        else if(!bcondition.matches("[a-zA-Z ]+"))
        {
            bookCondition.requestFocus();
            bookCondition.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            errors=true;
        }

        if(!errors){
            final ProgressDialog dlg = new ProgressDialog(Add_Book.this);
            dlg.setTitle("Please, wait a moment.");
            dlg.setMessage("Adding Book...");
            dlg.show();
            ParseObject addbook = new ParseObject("Add_Book");
            addbook.put("title",btitle);
            addbook.put("author",bauthor);
            addbook.put("isbn",bisbn);
            addbook.put("edition",bedition);
            addbook.put("condition",bcondition);
            addbook.put("useremail",MainActivity.email);
            addbook.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // Success
                        dlg.dismiss();
                        alertDisplayer("Book added successfully","");
                    } else {
                        // Error
                    }
                }
            });
        }
    }
    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_Book.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Add_Book.this, Add_Book.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
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



