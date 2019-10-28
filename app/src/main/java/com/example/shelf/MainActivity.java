package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
        if(email.getText().toString().isEmpty())
        {
            email.setError("Enter the valid Email ID");
        }
        else if(pwd.getText().toString().isEmpty())
        {
            pwd.setError("Enter the password");
        }
        else
        {
            Intent tip_intent = new Intent(this, home.class);
            startActivityForResult(tip_intent, 1);
        }

    }

    public void onSignup(View view) {
        Intent tip_intent = new Intent(this, Signup.class);
        startActivityForResult(tip_intent, 1);
    }
}
