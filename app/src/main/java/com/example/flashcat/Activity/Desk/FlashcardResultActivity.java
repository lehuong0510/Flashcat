package com.example.flashcat.Activity.Desk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.flashcat.R;

public class FlashcardResultActivity extends AppCompatActivity {
    private TextView txtKnow;
    private TextView txtLearning;
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_result);
        findID();
        Intent i = getIntent();
        Bundle b = i.getExtras();

        int know = b.getInt("Know");
        int learning = b.getInt("Learning");
        txtKnow.setText(String.valueOf(know));
        txtLearning.setText(String.valueOf(learning));

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void findID(){
        txtKnow = findViewById(R.id.txt_know);
        txtLearning = findViewById(R.id.txt_learning);
        btnClose = findViewById(R.id.btn_close_result_flashcard);
    }


}