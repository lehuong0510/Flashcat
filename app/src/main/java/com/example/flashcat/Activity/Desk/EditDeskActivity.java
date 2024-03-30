package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.flashcat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class EditDeskActivity extends AppCompatActivity {
     private Toolbar toolbarEditDesk;
     private TextInputEditText edDesk;
     private RecyclerView listFlashcard;
     private Button btnSaveEditDesk;
     private FloatingActionButton floatingActionAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_desk);
        findID();
        setSupportActionBar(toolbarEditDesk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingActionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditDeskActivity.this, CreateFlashcardActivity.class);
                startActivity(i);
            }
        });
        btnSaveEditDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
    public void findID(){
        toolbarEditDesk = findViewById(R.id.toolbar_EditDesk);
        edDesk = findViewById(R.id.ed_name_desk_edit);
        listFlashcard = findViewById(R.id.list_flashcard);
        floatingActionAdd = findViewById(R.id.floating_action_add);
        btnSaveEditDesk = findViewById(R.id.action_save_edit_desk);
    }
}