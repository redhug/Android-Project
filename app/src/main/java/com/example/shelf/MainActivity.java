package com.example.shelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailID;
    private EditText password;
    public static String email;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailID =  findViewById(R.id.email);
        password = findViewById(R.id.password);


        final Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validating the log in data
                boolean validationError = false;

                StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
                if (isEmpty(emailID)) {
                    validationError = true;
                    validationErrorMessage.append("an username");
                }
                if (isEmpty(password)) {
                    if (validationError) {
                        validationErrorMessage.append(" and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("a password");
                }
                validationErrorMessage.append(".");

                if (validationError) {
                    Toast.makeText(MainActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }

                //Setting up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(MainActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Logging in...");
                dlg.show();

                ParseUser.logInInBackground(emailID.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            dlg.dismiss();
                            alertDisplayer("Sucessful Login","Welcome back " + emailID.getText().toString() + "!");

                        } else {
                            dlg.dismiss();
                            ParseUser.logOut();
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        final Button signup_button = findViewById(R.id.Signup);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        email= emailID.getText().toString();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("email", emailID.getText().toString());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}
