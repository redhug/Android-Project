package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view) {
        EditText email=findViewById(R.id.email);
        EditText pwd=findViewById(R.id.password);
//        Intent signup=getIntent();
//        String a=signup.getStringExtra("Email");
//        String p=signup.getStringExtra("Password");
//        if(a.equals(email.getText().toString())&&p.equals(pwd.getText().toString()))
//        {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivityForResult(homeIntent, 1);
//        }
//        else {
//            email.setError("Enter the valid Email ID");
//            pwd.setError("Enter the password");
//        }

    }

    public void onSignup(View view) {
        Intent signUp = new Intent(this, SignupActivity.class);
        startActivityForResult(signUp, 1);
    }
}
