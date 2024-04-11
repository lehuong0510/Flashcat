package com.example.flashcat.Activity.SettingUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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
import android.widget.TextView;

import com.example.flashcat.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;

public class StorageActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnClear;
    private TextView txtData;
    private TextView txtCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        findID();

        // Lấy và hiển thị dung lượng data
        long dataSize = getAppDataSize();
        String formattedDataSize = formatSize(dataSize);
        txtData.setText(formattedDataSize);
        // Dung lượng cache
        long cacheSize = getCacheSize();
        String formattedCacheSize = formatSize(cacheSize);
        txtCache.setText(formattedCacheSize);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogClear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void findID(){

        btnBack = findViewById(R.id.back_storage);
        btnClear = findViewById(R.id.action_clear);
        txtData = findViewById(R.id.txt_data);
        txtCache = findViewById(R.id.txt_cache);
    }
    private long getCacheSize() {
        File cacheDir = getCacheDir(); // Thư mục cache của ứng dụng
        return getDirectorySize(cacheDir);
    }
    private long getAppDataSize() {
        PackageManager packageManager = getPackageManager();
        String packageName = getPackageName();
        try {
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            String appDir = appInfo.sourceDir;
            long appSize = new File(appDir).length(); // Dung lượng ứng dụng

            String dataDir = appInfo.dataDir;
            File dataDirectory = new File(dataDir);
            long dataSize = getDirectorySize(dataDirectory); // Dung lượng dữ liệu người dùng

            // Lấy dung lượng cache của ứng dụng
            File cacheDir = getCacheDir();
            long cacheSize = getDirectorySize(cacheDir);

            return appSize + dataSize + cacheSize; // Tổng dung lượng (ứng dụng + dữ liệu + cache)
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private long getDirectorySize(File directory) {
        long directorySize = 0;
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        directorySize += file.length();
                    } else if (file.isDirectory()) {
                        directorySize += getDirectorySize(file);
                    }
                }
            }
        }
        return directorySize;
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
                                deleteAppData();
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

                                deleteAppCache();
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
    private String formatSize(long size) {
        if (size <= 0) return "0 KB";

        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));


        double adjustedSize = size;
        int unitIndex = 0;
        while (adjustedSize >= 1024 && unitIndex < units.length - 1) {
            adjustedSize /= 1024;
            unitIndex++;
        }

        // Định dạng kết quả với đơn vị và số lượng chữ số thập phân phù hợp
        return String.format("%.2f %s", adjustedSize, units[unitIndex]);
    }
    // delete Data
    private void deleteAppData() {
        File dataDir = getFilesDir(); // Thư mục dữ liệu của ứng dụng
        deleteRecursive(dataDir);
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] files = fileOrDirectory.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteRecursive(child);
                }
            }
        }
        fileOrDirectory.delete();
    }
    // delete Cache
    private void deleteAppCache() {
        File cacheDir = getCacheDir(); // Thư mục cache của ứng dụng
        deleteRecursive(cacheDir);
    }

}