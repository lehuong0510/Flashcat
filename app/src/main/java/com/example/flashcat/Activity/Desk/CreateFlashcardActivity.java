package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

public class CreateFlashcardActivity extends AppCompatActivity {
    private DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Toolbar toolbarNewFlashcard;
    private Spinner spSelectDesk;
    private EditText edFront;
    private EditText edBack;
    private Button btnDoneNewFlashcard;
    private ArrayList<String> deskNames;
    public DatabaseApp db;
    private ArrayList<Desk> desks;
    int selectedDeskId;
    String selectedDeskName;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_create_flashcard);
        findID();
        setSupportActionBar(toolbarNewFlashcard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         deskNames = new ArrayList<>();
        //Them ten tu db vao list
        db = new DatabaseApp(CreateFlashcardActivity.this);
        desks  = new ArrayList<>();
             desks   = db.getAllDesk();
        for (Desk desk : desks) {
            deskNames.add(desk.getName_deck());
        }
        // Tạo Adapter và thiết lập cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deskNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSelectDesk.setAdapter(adapter);
        spSelectDesk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDeskId = desks.get(position).getID_Deck();
                selectedDeskName = desks.get(position).getName_deck();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDoneNewFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                String currentDateTimeString = currentDateTime.format(DATE_TIME_FORMATTER);

                db.addFlashcard(new Flashcard(1,edFront.getText().toString(),edBack.getText().toString(),"K","S",false,LocalDateTime.parse(currentDateTimeString,DATE_TIME_FORMATTER),selectedDeskId));
                Intent i = new Intent(CreateFlashcardActivity.this,DeskActivity.class);
                i.putExtra("ID_Desk",selectedDeskId);
                i.putExtra("Name_Desk",selectedDeskName);
                startActivity(i);

            }
        });
    }
    //event toolbar

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
        toolbarNewFlashcard = findViewById(R.id.toolbar_NewFlashcard);
        spSelectDesk = findViewById(R.id.sp_select_desk);
        edFront = findViewById(R.id.ed_front_flashcard);
        edBack = findViewById(R.id.ed_back_flashcard);
        btnDoneNewFlashcard = findViewById(R.id.action_done_new_flashcard);

    }
}