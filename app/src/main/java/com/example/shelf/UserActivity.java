package com.example.shelf;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    TextView contact;
    TextView email;
    TextView address;
    TextView username;
    public static String bookuseraddress;
    // public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        contact=findViewById(R.id.contact);
        email=(TextView) findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);

        System.out.println(Find_Book.useremail);
        email.setText(Find_Book.useremail);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email",Find_Book.useremail);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    for(ParseUser user : users) {
                        username.setText(user.getUsername());
                        contact.setText(user.getString("Contact"));
                        address.setText(user.getString("Address"));
                       bookuseraddress=user.getString("Address");

                    }
                } else {
                   Log.d("Exception occured","e");
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

    public void onClickSendRequest(View view) {
        final ProgressDialog dlg = new ProgressDialog(UserActivity.this);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Sending Request...");
        dlg.show();
        ParseObject request = new ParseObject("request");
        request.put("senderemail",MainActivity.email);
        request.put("recepientemial",Find_Book.useremail);
        request.put("title",Find_Book.booktitle);
        request.put("requesttype","sent");
        request.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Success
                    dlg.dismiss();
                    alertDisplayer("Request sent successfully","");
                } else {
                    Log.d("Exception occured","e");
                }
            }
        });
    }
    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void maps(View view) {
        Intent intent=new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}
