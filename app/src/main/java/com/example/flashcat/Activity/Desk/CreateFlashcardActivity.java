package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flashcat.R;

public class CreateFlashcardActivity extends AppCompatActivity {
    private Toolbar toolbarNewFlashcard;
    private Spinner spSelectDesk;
    private EditText edFront;
    private EditText edBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);
        findID();
        setSupportActionBar(toolbarNewFlashcard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //event toolbar

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            onBackPressed();
            return true;
        }
        else if(it == R.id.action_done_new_flashcard){
            Intent i = new Intent(CreateFlashcardActivity.this, DeskActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void findID(){
        toolbarNewFlashcard = findViewById(R.id.toolbar_NewFlashcard);
        spSelectDesk = findViewById(R.id.sp_select_desk);
        edFront = findViewById(R.id.ed_front_flashcard);
        edBack = findViewById(R.id.ed_back_flashcard);

    }
}