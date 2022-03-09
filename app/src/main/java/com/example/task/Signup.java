package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText mName,mUserName,mPassword,mRePassword;
    private Button mSignUp,mLogin;
    DBHelper DB;
    private Spinner mGender;

    String[] genderArr={"Male","Female","Other"};
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mGender = findViewById(R.id.spinner);
        mGender.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genderArr);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGender.setAdapter(ad);

        mName = (EditText) findViewById(R.id.name);
        mUserName = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        mRePassword = (EditText) findViewById(R.id.retypePassword);
        mSignUp = (Button) findViewById(R.id.supSignUp);
        mLogin = (Button) findViewById(R.id.supLogin);
        DB = new DBHelper(this);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String user = mUserName.getText().toString();
                String pass = mPassword.getText().toString();
                String rePass = mRePassword.getText().toString();
                if(name.equals("") || user.equals("") || pass.equals("") || rePass.equals("")){
                    Toast.makeText(Signup.this,"Enter All Fields",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(rePass)){
                        Boolean checkUser = DB.checkusername(user);
                        if(checkUser==false){
                            Boolean insert = DB.insertData(name,user,pass);
                            if(insert==true){
                                Toast.makeText(Signup.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Signup.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        gender=genderArr[i];
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}