package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcat.Model.Account;
import com.example.flashcat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView btnChangeImage;
    private CircleImageView edProfileImage;
    private EditText edProfileFirstname;
    private EditText edProfileLastname;
    private EditText edProfileUsername;
    private EditText edProfileEmail;
    private Button btnUpdate;
    private Account acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findID();


        loadProfileData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void findID(){
        btnBack = findViewById(R.id.back_editProfile);
        btnChangeImage = findViewById(R.id.btn_change_image);
        edProfileImage = findViewById(R.id.ed_profile_image);
        edProfileFirstname = findViewById(R.id.ed_profile_first_name);
        edProfileLastname = findViewById(R.id.ed_profile_last_name);
        edProfileUsername = findViewById(R.id.ed_profile_username);
        edProfileEmail = findViewById(R.id.ed_profile_email);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
    private void loadProfileData() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            // Tạo ID từ email
            String userId = userEmail.replace("@gmail.com", "");

            DatabaseReference profilesRef = FirebaseDatabase.getInstance().getReference().child("accounts").child(userId);
            profilesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String firstname = dataSnapshot.child("first_name").getValue(String.class);
                        String lastname = dataSnapshot.child("last_name").getValue(String.class);
                        String username = dataSnapshot.child("username").getValue(String.class);

                        edProfileFirstname.setText(firstname);
                        edProfileLastname.setText(lastname);
                        edProfileUsername.setText(username);
                        edProfileEmail.setText(userEmail);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void updateProfile() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String firstname = edProfileFirstname.getText().toString().trim();
            String lastname = edProfileLastname.getText().toString().trim();
            String username = edProfileUsername.getText().toString().trim();
            acc = new Account(firstname,lastname,username);

            if (TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(username)) {
                Toast.makeText(ProfileActivity.this, "Please enter complete information", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!acc.validate_first_name(acc.getFirst_name())){
                Toast.makeText(this, "First name is not in the correct format",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!acc.validate_last_name(acc.getLast_name())){
                Toast.makeText(this,"Last name is not in the correct format",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!acc.validate_username(acc.getUsername())){
                Toast.makeText(this, "User name is not in the correct format",Toast.LENGTH_SHORT).show();
                return;
            }


            String userEmail = currentUser.getEmail();
            String userId = userEmail.replace("@gmail.com", "");

            DatabaseReference profilesRef = FirebaseDatabase.getInstance().getReference().child("accounts").child(userId);

            Map<String, Object> updateData = new HashMap<>();
            updateData.put("first_name", firstname);
            updateData.put("last_name", lastname);
            updateData.put("username", username);

            profilesRef.updateChildren(updateData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


}