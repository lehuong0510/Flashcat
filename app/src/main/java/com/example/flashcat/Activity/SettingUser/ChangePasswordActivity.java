package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.flashcat.Model.Account;
import com.example.flashcat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordActivity extends AppCompatActivity {

    private Toolbar toolbarPassword;
    private EditText edOldPassword;
    private ImageButton btnOldPassword;
    private EditText edNewPassword;
    private ImageButton btnNewPassword;
    private EditText edConfirmPassword;
    private Button btnChangePassword;
    private ImageButton btnConfirmPassword;
    private Account acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        findID();
        setSupportActionBar(toolbarPassword);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnOldPassword.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (!isPasswordVisible) {
                    edOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPasswordVisible = true;
                } else {
                    edOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = false;
                }
            }
        });
        btnNewPassword.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (!isPasswordVisible) {
                    edNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPasswordVisible = true;
                } else {
                    edNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = false;
                }
            }
        });
        btnConfirmPassword.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (!isPasswordVisible) {
                    edConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPasswordVisible = true;
                } else {
                    edConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = false;
                }
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = edOldPassword.getText().toString().trim();
                String newPassword = edNewPassword.getText().toString().trim();
                String confirmPassword = edConfirmPassword.getText().toString().trim();

                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter complete information", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(ChangePasswordActivity.this, "New password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (oldPassword.equals(newPassword)) {
                    Toast.makeText(ChangePasswordActivity.this, "The new password cannot be the same as the old password", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                AuthCredential credential = EmailAuthProvider.getCredential(userEmail, oldPassword);

                FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("accounts");
                                usersRef.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                                String userId = userSnapshot.getKey();
                                                DatabaseReference userRef = usersRef.child(userId);
                                                userRef.child("password").setValue(newPassword)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(ChangePasswordActivity.this, "Password was successfully changed", Toast.LENGTH_SHORT).show();
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Toast.makeText(ChangePasswordActivity.this, "Unable to update new password", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Xử lý khi có lỗi xảy ra
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ChangePasswordActivity.this, "The old password is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        });
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