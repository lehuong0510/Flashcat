package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.flashcat.Adapter.EditDeskAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditDeskActivity extends AppCompatActivity {
     private Toolbar toolbarEditDesk;
     private TextInputEditText edDesk;
     private RecyclerView recyclerViewFlashcard;
     private Button btnSaveEditDesk;
     private FloatingActionButton floatingActionAdd;
     private int idDesk;
    private String nameDesk;
    private ArrayList<Flashcard> listFlashcard;
    private EditDeskAdapter adapter;

    private DatabaseApp db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_desk);
        findID();
        setSupportActionBar(toolbarEditDesk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseApp(EditDeskActivity.this);
        listFlashcard = new ArrayList<Flashcard>();
        //nhan id can sua
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                idDesk = extras.getInt("ID_Desk");
                nameDesk = extras.getString("NameDesk");
            }}
        Log.d("edit", "onCreate: "+ idDesk +" "+ nameDesk);
        edDesk.setText(nameDesk);
        listFlashcard = db.getAllContactDesk(idDesk);
        adapter = new EditDeskAdapter(listFlashcard,EditDeskActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewFlashcard.setLayoutManager(layoutManager);
        recyclerViewFlashcard.setAdapter(adapter);
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

                for(Flashcard list: listFlashcard )
                {
                    db.updateFlashcard(list.getID_Flashcard(),list);
                    Log.d("update", "id flashcard: " + list.getID_Flashcard() +" iddesk: " + list.getTerm());
                }
                Intent i = new Intent(EditDeskActivity.this,DeskActivity.class);
                i.putExtra("ID_Desk",idDesk);
                i.putExtra("NameDesk", nameDesk);
                setResult(210,i);
                finish();
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
        recyclerViewFlashcard = findViewById(R.id.list_flashcard);
        floatingActionAdd = findViewById(R.id.floating_action_add);
        btnSaveEditDesk = findViewById(R.id.action_save_edit_desk);
    }
}