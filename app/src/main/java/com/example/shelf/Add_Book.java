package com.example.shelf;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public class Add_Book extends AppCompatActivity {

    EditText bookTitle;
    EditText author;
    EditText isbn;
    EditText edition;
    EditText bookCondition;
    Button submit;
    ImageView imageView;

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
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(Add_Book.this);
            }
        });
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        if (selectedImage != null) {
                            try {
                                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                                imageView.setImageBitmap(image);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                    break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(this, HomeActivity.class);
                startActivityForResult(home, 1);
                return (true);
            case R.id.findBook:
                Intent findBook = new Intent(this, Find_Book.class);
                startActivityForResult(findBook, 1);
                return (true);
            case R.id.requests:
                Intent requests = new Intent(this, Activity_RequestRecieved.class);
                startActivityForResult(requests, 1);
                return (true);
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivityForResult(profile, 1);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void buttonSave_onClick(View v) {
        final String btitle = bookTitle.getText().toString();
        final String bisbn = isbn.getText().toString();
        final String bauthor = author.getText().toString();
        final String bedition = edition.getText().toString();
        final String bcondition = bookCondition.getText().toString();
        Bitmap bimage = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        final String image = getEncoded64ImageStringFromBitmap(bimage);
        boolean errors = false;
        if (btitle.length() == 0) {
            bookTitle.requestFocus();
            bookTitle.setError("cannot be empty!!");
            errors = true;
        }
        if (bauthor.length() == 0) {
            author.requestFocus();
            author.setError("cannot be empty!!");
            errors = true;
        } else if (!bauthor.matches("[a-zA-Z ]+")) {
            author.requestFocus();
            author.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            errors = true;
        }
        if (bisbn.length() == 0) {
            isbn.requestFocus();
            isbn.setError("cannot be empty!!");
            errors = true;
        } else if (!Pattern.matches("[0-9]+", bisbn)) {
            isbn.requestFocus();
            errors = true;
            isbn.setError("isbn field should contain only numerical values");
        }
        if (bedition.length() == 0) {
            edition.requestFocus();
            edition.setError("cannot be empty!!");
            errors = true;
        } else if (!Pattern.matches("[0-9]+", bedition)) {
            edition.requestFocus();
            errors = true;
            isbn.setError("bedition field should contain only numerical values");
        }
        if (bcondition.length() == 0) {
            bookCondition.requestFocus();
            bookCondition.setError("cannot be empty!!");
            errors = true;
        } else if (!bcondition.matches("[a-zA-Z ]+")) {
            bookCondition.requestFocus();
            bookCondition.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            errors = true;
        }

        if (!errors) {
            final ProgressDialog dlg = new ProgressDialog(Add_Book.this);
            dlg.setTitle("Please, wait a moment.");
            dlg.setMessage("Adding Book...");
            dlg.show();
            ParseObject addbook = new ParseObject("Add_Book");
            addbook.put("title", btitle.toUpperCase());
            addbook.put("author", bauthor);
            addbook.put("isbn", bisbn);
            addbook.put("edition", bedition);
            addbook.put("condition", bcondition);
            addbook.put("useremail", MainActivity.email);
            addbook.put("image", image);
            addbook.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // Success
                        dlg.dismiss();
                        alertDisplayer("Book added successfully", "");
                    } else {
                        Log.d("Exception occured", "e");
                    }
                }
            });
        }
    }

    private void alertDisplayer(String title, String message) {
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
//    public void onActivityResult(int requestCode,int resultCode,Intent tipInt) {
//        try {
//            if (requestCode == 1) {
//                if (resultCode == 0) {
//                    Log.d("Success", "got result");
//                }
//            }
//        }
//        catch (Exception e){
//        }
//    }

}


