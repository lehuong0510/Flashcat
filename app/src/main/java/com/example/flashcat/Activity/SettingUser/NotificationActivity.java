package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.flashcat.R;
import com.google.android.material.materialswitch.MaterialSwitch;

public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbarNotification;
    private MaterialSwitch swPushNotification;
    private MaterialSwitch swWordReminder;
    private Spinner spDeskRemind;
    private Button btnTimeStart;
    private Button btnTimeFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findID();

        setSupportActionBar(toolbarNotification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swPushNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        swWordReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        btnTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTimeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void findID(){
        toolbarNotification = findViewById(R.id.toolbar_Notification);
        swPushNotification = findViewById(R.id.switch_push_notification);
        swWordReminder = findViewById(R.id.switch_word_reminder);
        spDeskRemind = findViewById(R.id.sp_select_desk_remind);
        btnTimeStart = findViewById(R.id.btn_select_timeStart);
        btnTimeFinish = findViewById(R.id.btn_select_timeFinish);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            onBackPressed();
            return true;
        }
        else if(it == R.id.btn_notification_save){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}