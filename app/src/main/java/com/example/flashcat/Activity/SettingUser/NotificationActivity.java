package com.example.flashcat.Activity.SettingUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.flashcat.R;
import com.google.android.material.materialswitch.MaterialSwitch;

public class NotificationActivity extends AppCompatActivity {
    private ImageButton btnBackNotification;
    private Button btnSaveNotification;
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

        btnBackNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSaveNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        btnBackNotification = findViewById(R.id.btn_back_notification);
        btnSaveNotification = findViewById(R.id.btn_notification_save);
        swPushNotification = findViewById(R.id.switch_push_notification);
        swWordReminder = findViewById(R.id.switch_word_reminder);
        spDeskRemind = findViewById(R.id.sp_select_desk_remind);
        btnTimeStart = findViewById(R.id.btn_select_timeStart);
        btnTimeFinish = findViewById(R.id.btn_select_timeFinish);
    }
}