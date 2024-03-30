package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flashcat.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbarProfile;
    private TextView btnChangeImage;
    private CircleImageView edProfileImage;
    private EditText edProfileFirstname;
    private EditText edProfileLastname;
    private EditText edProfileUsername;
    private EditText edProfileEmail;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findID();
        toolbarProfile = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbarProfile);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Thiết lập biểu tượng "quay lại" và thay đổi màu
            Drawable backArrow = getResources().getDrawable(R.drawable.back);
            backArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(backArrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void findID(){
        toolbarProfile = findViewById(R.id.toolbarProfile);
        btnChangeImage = findViewById(R.id.btn_change_image);
        edProfileImage = findViewById(R.id.ed_profile_image);
        edProfileFirstname = findViewById(R.id.ed_profile_first_name);
        edProfileLastname = findViewById(R.id.ed_profile_last_name);
        edProfileUsername = findViewById(R.id.ed_profile_username);
        edProfileEmail = findViewById(R.id.ed_profile_email);
        btnUpdate = findViewById(R.id.btnUpdate);
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