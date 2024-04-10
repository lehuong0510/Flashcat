package com.example.flashcat.Activity.Practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import java.util.ArrayList;

public class TrueFalseActivity extends AppCompatActivity {

    ArrayList<Flashcard> flashcards;
    int questionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        // Get data from intent
        Intent intent = getIntent();
        flashcards = (ArrayList<Flashcard>) intent.getSerializableExtra("FLASHCARDS");
        questionType = intent.getIntExtra("QUESTION_TYPE", 0);

        // Rest of your code...
    }
}
