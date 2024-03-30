package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.flashcat.R;

public class FlashcardActivity extends AppCompatActivity {
    private Toolbar toolbarFlashcard;
    private RecyclerView listItemFlashcard;
    private ImageButton btnReturnFlashcard;
    private ImageButton btnFlashcardStar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        findID();
        setSupportActionBar(toolbarFlashcard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public  void findID(){
        toolbarFlashcard = findViewById(R.id.toolbar_Flashcard);
        listItemFlashcard = findViewById(R.id.list_item_flashcard);
        btnReturnFlashcard = findViewById(R.id.btn_return_flashcard);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}