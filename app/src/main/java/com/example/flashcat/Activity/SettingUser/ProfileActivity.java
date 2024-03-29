package com.example.flashcat.Activity.SettingUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.flashcat.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbarProfile;
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

        finID();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void finID(){
        toolbarProfile = findViewById(R.id.toolbarProfile);
        edProfileImage = findViewById(R.id.ed_profile_image);
        edProfileFirstname = findViewById(R.id.ed_profile_first_name);
        edProfileLastname = findViewById(R.id.ed_profile_last_name);
        edProfileUsername = findViewById(R.id.ed_profile_username);
        edProfileEmail = findViewById(R.id.ed_profile_email);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}