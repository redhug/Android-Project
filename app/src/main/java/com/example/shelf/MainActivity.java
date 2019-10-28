package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onlogin(View view) {
        EditText email=findViewById(R.id.email);
        EditText pwd=findViewById(R.id.password);
        Intent signup=getIntent();
        String a=signup.getStringExtra("Email");
        String p=signup.getStringExtra("Password");
        if(a.equals(email.getText().toString())&&p.equals(pwd.getText().toString()))
        {
            Intent tip_intent = new Intent(this, home.class);
            startActivityForResult(tip_intent, 1);
        }
        else {
            email.setError("Enter the valid Email ID");
            pwd.setError("Enter the password");
        }

    }

    public void onSignup(View view) {
        Intent tip_intent = new Intent(this, Signup.class);
        startActivityForResult(tip_intent, 1);
    }
}
