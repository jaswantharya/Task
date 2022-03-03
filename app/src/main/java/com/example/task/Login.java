package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText mUserName, mPassword;
    private Button mLogin, mSignUp;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = findViewById(R.id.edtTxtUsername);
        mPassword = findViewById(R.id.edtTxtPassword);
        mLogin = findViewById(R.id.btnLogin);
        mSignUp = findViewById(R.id.btnSignup);
        DB = new DBHelper(this);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mUserName.getText().toString();
                String pass = mPassword.getText().toString();
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(Login.this, "Enter Details!!", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUserPass= DB.checkusernamepassword(user,pass);
                    if(checkUserPass==true){
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}