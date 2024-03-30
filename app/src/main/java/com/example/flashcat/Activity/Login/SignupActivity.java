package com.example.flashcat.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flashcat.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText edFirstName;
    private TextInputEditText edLastName;
    private TextInputEditText edEmailSignup;
    private TextInputEditText edUsername;
    private TextInputEditText edPasswordSignup;
    private TextInputEditText edRetypePassword;
    private Button btnRegister;
    private TextView btnSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findID();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    public void findID(){
        edFirstName = findViewById(R.id.ed_first_name);
        edLastName = findViewById(R.id.ed_last_name);
        edEmailSignup = findViewById(R.id.ed_email_singup);
        edUsername = findViewById(R.id.ed_username);
        edPasswordSignup = findViewById(R.id.ed_password_signup);
        edRetypePassword = findViewById(R.id.ed_retype_password);
        btnRegister = findViewById(R.id.btn_Register);
        btnSignin = findViewById(R.id.btn_signin);
    }
}