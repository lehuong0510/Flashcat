package com.example.flashcat.Activity.Desk;

import static android.graphics.Color.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.flashcat.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class FlashcardResultActivity extends AppCompatActivity {
    private TextView txtKnow;
    private TextView txtLearning;
    private ImageButton btnClose;
    private PieChart result;

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
        //pie chat
        ArrayList<PieModel> pieModels = new ArrayList<>();
        pieModels.add(new PieModel("know", know, parseColor("#66BB6A")));
        pieModels.add(new PieModel("learning", learning, parseColor("#F45F5F")));

        // Set the percentage values directly on each PieModel
        for (PieModel pieModel : pieModels) {
            pieModel.setShowLabel(true); // Hiển thị phần trăm trong mỗi phần của biểu đồ

            // Thiết lập màu nền của nhãn (phần trăm) là trong suốt (transparent)
            pieModel.setHighlightedColor(TRANSPARENT);

            result.addPieSlice(pieModel);
        }

        result.startAnimation();



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
        result = findViewById(R.id.pie_chart);
    }


}