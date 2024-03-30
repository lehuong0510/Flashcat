package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.flashcat.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class StorageActivity extends AppCompatActivity {
    private Toolbar toolbarSrorage;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        findID();
        setSupportActionBar(toolbarSrorage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogClear();
            }
        });
    }
    public void findID(){

        toolbarSrorage = findViewById(R.id.toolbar_Storage);
        btnClear = findViewById(R.id.action_clear);
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
    private void showDialogClear() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_clear_storage);

        Button btnClearData = dialog.findViewById(R.id.btn_delete_data);
        Button btnClearDataCache = dialog.findViewById(R.id.btn_delete_data_cache);


        btnClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                View overlay = findViewById(R.id.overlay);
                overlay.setVisibility(View.VISIBLE);
                new MaterialAlertDialogBuilder(StorageActivity.this) // Chú ý cần thêm "this" hoặc tên Activity
                        .setTitle(getResources().getString(R.string.title_dialog))
                        .setMessage(getResources().getString(R.string.supporting_text))
                        .setNeutralButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi nhấn nút neutral
                                overlay.setVisibility(View.GONE);

                            }
                        })

                        .setPositiveButton(getResources().getString(R.string.accept), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi nhấn nút positive
                                overlay.setVisibility(View.GONE);

                            }
                        })
                        .show();


            }
        });
        btnClearDataCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                View overlay = findViewById(R.id.overlay);
                overlay.setVisibility(View.VISIBLE);
                new MaterialAlertDialogBuilder(StorageActivity.this) // Chú ý cần thêm "this" hoặc tên Activity
                        .setTitle(getResources().getString(R.string.title_dialog))
                        .setMessage(getResources().getString(R.string.supporting_text))
                        .setNeutralButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi nhấn nút neutral
                                overlay.setVisibility(View.GONE);

                            }
                        })

                        .setPositiveButton(getResources().getString(R.string.accept), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi nhấn nút positive
                                overlay.setVisibility(View.GONE);

                            }
                        })
                        .show();


            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        View overlay = findViewById(R.id.overlay);
        overlay.setVisibility(View.VISIBLE);

    }
}