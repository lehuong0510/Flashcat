package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.flashcat.R;
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingActivity extends AppCompatActivity {
    private Toolbar toolbarSetting;
    private Button btnEditProfile;
    private Button btnChangePassword;
    private Button btnNotiSetting;
    private Button btnManageStorage;
    private MaterialSwitch swSoundEffect;
    private MaterialSwitch swDarkMode;
    private Button btnAboutUs;
    private Button btnPrivacyPolicy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findID();

        setSupportActionBar(toolbarSetting);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Thiết lập biểu tượng "quay lại" và thay đổi màu
            Drawable backArrow = getResources().getDrawable(R.drawable.back);
            backArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(backArrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(i);
            }
        });
        btnNotiSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });
        btnManageStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, StorageActivity.class);
                startActivity(i);
            }
        });
        swSoundEffect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        swDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void findID()
    {
        toolbarSetting = findViewById(R.id.toolbarSetting);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        btnChangePassword = findViewById(R.id.btn_change_password);
        btnNotiSetting = findViewById(R.id.btn_notification_settings);
        btnManageStorage = findViewById(R.id.btn_manage_storage);
        swSoundEffect = findViewById(R.id.switch_sound_effect);
        swDarkMode = findViewById(R.id.switch_dark_mode);
        btnAboutUs = findViewById(R.id.btn_about_us);
        btnPrivacyPolicy = findViewById(R.id.btn_privacy_policy);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}