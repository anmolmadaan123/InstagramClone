package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtusernamesignup,edtusernamelogin,edtPasswordsignup,edtpasswordlogin;
    private Button btnSignup,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtusernamesignup=findViewById(R.id.edtusernameSignup);
        edtusernamelogin=findViewById(R.id.edtnamelogin);
        edtPasswordsignup=findViewById(R.id.edtpasswordSignup);
        edtpasswordlogin=findViewById(R.id.edtpasswordlogin);

        btnSignup=findViewById(R.id.btnSignup);
        btnLogin=findViewById(R.id.btnlogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser=new ParseUser();
                appUser.setUsername(edtusernamesignup.getText().toString());
                appUser.setPassword(edtPasswordsignup.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is signed up successfully", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtusernamelogin.getText().toString(), edtpasswordlogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!=null&&e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username")+" is logged in successfully", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else{
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });

    }
}
