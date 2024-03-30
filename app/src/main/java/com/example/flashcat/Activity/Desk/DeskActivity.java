package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.flashcat.R;

public class DeskActivity extends AppCompatActivity {
    private Toolbar toolbarDesk ;
    private Button btnStudy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);
        findID();
        setSupportActionBar(toolbarDesk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void findID(){
        toolbarDesk = findViewById(R.id.toolbar_Desk);
        btnStudy = findViewById(R.id.btn_Study);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            onBackPressed();
            return true;
        }
        else if(it == R.id.action_more_desk){
            showPopupMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_more_desk));
        popupMenu.getMenuInflater().inflate(R.menu.menu_desk, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện
                int it= item.getItemId();
                if(it == R.id.menu_Edit){
                    Intent i = new Intent(DeskActivity.this, EditDeskActivity.class);
                    startActivity(i);
                }
                else if(it == R.id.menu_Delete){

                }
                return true;
            }
        });
        popupMenu.show();
    }

}