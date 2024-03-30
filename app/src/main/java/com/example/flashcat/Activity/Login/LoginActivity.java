package com.example.flashcat.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.flashcat.Activity.HomeActivity;
import com.example.flashcat.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edEmailLogin;
    private TextInputEditText edPasswordLogin;
    private CheckBox cbRemember;
    private TextView btnForgot;
    private Button btnLogin;
    private TextView btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findID();
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivityForResult(i,100);
            }
        });
    }
    public void findID()
    {
        edEmailLogin = findViewById(R.id.ed_email_login);
        edPasswordLogin = findViewById(R.id.ed_password_login);
        cbRemember = findViewById(R.id.cb_Remember);
        btnForgot = findViewById(R.id.btn_Forgot);
        btnLogin = findViewById(R.id.btn_Login);
        btnSignup = findViewById(R.id.btn_signup);
    }
}