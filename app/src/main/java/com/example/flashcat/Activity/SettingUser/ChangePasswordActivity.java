package com.example.flashcat.Activity.SettingUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.flashcat.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageButton btnBackChangePassword;
    private Button btnChangePassword;
    private EditText edOldPassword;
    private ImageButton btnOldPassword;
    private EditText edNewPassword;
    private ImageButton btnNewPassword;
    private EditText edConfirmPassword;
    private ImageButton btnConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        findID();

        btnBackChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnOldPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void findID()
    {
        btnBackChangePassword = findViewById(R.id.btn_back_change_password);
        btnChangePassword = findViewById(R.id.btn_change_password);
        edOldPassword = findViewById(R.id.ed_old_password);
        btnOldPassword = findViewById(R.id.btn_old_password);
        edNewPassword = findViewById(R.id.ed_new_password);
        btnOldPassword = findViewById(R.id.btn_old_password);
        edConfirmPassword =  findViewById(R.id.ed_confirm_password);
        btnConfirmPassword = findViewById(R.id.btn_confirm_password);
    }
}