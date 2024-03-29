package com.example.flashcat.Activity.SettingUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.flashcat.R;

public class SettingActivity extends AppCompatActivity {
    private Button btnEditProfile;
    private Button btnChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}