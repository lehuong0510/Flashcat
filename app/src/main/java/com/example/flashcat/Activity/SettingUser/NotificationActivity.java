package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.R;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private MaterialSwitch swPushNotification;
    private MaterialSwitch swWordReminder;
    private Spinner spDeskRemind;
    private Button btnTimeStart;
    private Button btnTimeFinish;
    private Button btnSaveNotification;
    private DatabaseApp db;
    private static final int REQUEST_TIME_PICKER = 1;
    private static final int REQUEST_TIME_PICKER_FOR_FINISH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        db = new DatabaseApp(this);
        findID();



        ArrayList<Desk> desks = db.getAllDesk();
        ArrayList<String> deskNames = new ArrayList<>();
        for(Desk desk : desks){
            deskNames.add(desk.getName_deck());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,deskNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDeskRemind.setAdapter(adapter);
        spDeskRemind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDesk = deskNames.get(position);
                // Xử lý logic khi chọn desk
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có gì được chọn
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
                openTimePickerActivity();
            }
        });
        btnTimeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerActivityForFinish();
            }
        });
        btnSaveNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void findID(){
        btnBack = findViewById(R.id.back_notification);
        swPushNotification = findViewById(R.id.switch_push_notification);
        swWordReminder = findViewById(R.id.switch_word_reminder);
        spDeskRemind = findViewById(R.id.sp_select_desk_remind);
        btnTimeStart = findViewById(R.id.btn_select_timeStart);
        btnTimeFinish = findViewById(R.id.btn_select_timeFinish);
        btnSaveNotification = findViewById(R.id.btn_notification_save);
    }
    private void openTimePickerActivity() {
        // Tạo Intent để mở TimePickerActivity
        Intent intent = new Intent(NotificationActivity.this, TimePickerActivity.class);
        // Bắt đầu Activity để hiển thị giao diện đồng hồ
        startActivityForResult(intent, REQUEST_TIME_PICKER);
    }
    private void openTimePickerActivityForFinish() {
        Intent intent = new Intent(NotificationActivity.this, TimePickerActivity.class);
        startActivityForResult(intent, REQUEST_TIME_PICKER_FOR_FINISH);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TIME_PICKER && resultCode == RESULT_OK && data != null) {
            int hour = data.getIntExtra("hour", 0);
            int minute = data.getIntExtra("minute", 0);
            btnTimeStart.setText(String.format("%02d : %02d", hour, minute));
        } else if (requestCode == REQUEST_TIME_PICKER_FOR_FINISH && resultCode == RESULT_OK && data != null) {
            int hour = data.getIntExtra("hour", 0);
            int minute = data.getIntExtra("minute", 0);
            btnTimeFinish.setText(String.format("%02d : %02d", hour, minute));
        }
    }

}