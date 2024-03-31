package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.flashcat.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private Toolbar toolbarPassword;
    private EditText edOldPassword;
    private ImageButton btnOldPassword;
    private EditText edNewPassword;
    private ImageButton btnNewPassword;
    private EditText edConfirmPassword;
    private Button btnChangePassword;
    private ImageButton btnConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        findID();
        setSupportActionBar(toolbarPassword);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void findID()
    {
        toolbarPassword = findViewById(R.id.toolbar_Password);
        edOldPassword = findViewById(R.id.ed_old_password);
        btnOldPassword = findViewById(R.id.btn_old_password);
        edNewPassword = findViewById(R.id.ed_new_password);
        btnNewPassword = findViewById(R.id.btn_new_password);
        edConfirmPassword =  findViewById(R.id.ed_confirm_password);
        btnConfirmPassword = findViewById(R.id.btn_confirm_password);
        btnChangePassword = findViewById(R.id.action_changepassword);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}