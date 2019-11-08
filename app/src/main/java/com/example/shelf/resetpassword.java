package com.example.shelf;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class resetpassword extends AppCompatActivity {
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        email=findViewById(R.id.resetemail);
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(resetpassword.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(resetpassword.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void resetpass(View view) {
        ParseUser.requestPasswordResetInBackground("anveshrao1616@gmail.com",
                new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(resetpassword.this, "Please check your email.", Toast.LENGTH_LONG).show();
                            finish();
                            // An email was successfully sent with reset instructions.
                        } else {
                            Toast.makeText(resetpassword.this, "Error :: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            // Something went wrong. Look at the ParseException to see what's up.
                        }
                    }
                });
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
