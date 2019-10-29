package com.example.shelf;



import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText fullName;
    EditText mobile;
    EditText email;
    EditText pass;
    EditText confirmPass;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullName=findViewById(R.id.fullNameET);
        mobile=findViewById(R.id.mobileET);
        email =findViewById(R.id.userIdET);
        pass=findViewById(R.id.passwordET);
        confirmPass=findViewById(R.id.confirmPswdET);
        signUp=findViewById(R.id.signUpBTN1);
    }
    //SignIn action
    public void gotoSuccessfulSignUpActivityAction(View v) {
        //Fullname Field validation
        final String fName=fullName.getText().toString();
        final String mobNum=mobile.getText().toString();
        String user= email.getText().toString();
        final String p=pass.getText().toString();
        final String Cpass=confirmPass.getText().toString();
        if(fName.length()==0){
            fullName.requestFocus();
            fullName.setError("Name field cannot be empty!!");
        }
        else if(!fName.matches("[a-zA-Z ]+"))
        {
            fullName.requestFocus();
            fullName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        }

        //Mobile Number validation

        else if(mobNum.length()==0|| mobNum.length()>11){
            mobile.requestFocus();
            mobile.setError("Mobile Field is Empty/ too Long");
        }
        else if (!Pattern.matches("[0-9]+",mobNum)){
            mobile.setError("Mobile Field should contain only numerical values");
        }

        //UserId validation

        else if(user.length()==0){
            email.requestFocus();
            email.setError("Name field cannot be empty!!");
        }

        //Password validation
        else if(p.length()<8&&!isValidPassword(p)){
            pass.requestFocus();
            pass.setError("Enter Valid Password with atleast 1 capital letter, 1 small letter, 1 number and a symbol");
        }

        //Confirm Password Validation
        else if(!(Cpass.equals(p))){
            confirmPass.requestFocus();
            confirmPass.setError("Password and Confirm Password does not match");
        }

        else {
            try {
                Intent toOtherIntent = new Intent(this, MainActivity.class);
                toOtherIntent.putExtra("Email",user);
                toOtherIntent.putExtra("Password",p);
                startActivity(toOtherIntent);
            } catch (Exception e) {

            }
        }
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

}
