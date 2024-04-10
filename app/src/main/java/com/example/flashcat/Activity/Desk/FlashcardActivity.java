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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.flashcat.Adapter.FlashcatAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import java.util.ArrayList;

public class FlashcardActivity extends AppCompatActivity {
    private Toolbar toolbarFlashcard;
    private RecyclerView recyclerFlashcard;
    private ImageButton btnReturnFlashcard;
    private TextView txtNumber;
    private ImageButton btnFlashcardStar;
    private ArrayList<Flashcard> flashcardArrayList;
    private DatabaseApp db;
    private FlashcatAdapter adapter;
    private String flipFrom;
    private  int idFlashcard;
    private int idDesk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        findID();
        setSupportActionBar(toolbarFlashcard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Nhan du lieu
        Intent intent = getIntent();
        if(intent!=null){
            Bundle extras = intent.getExtras();
            if (extras != null) {
                flipFrom = extras.getString("flipFrom");
                idFlashcard = extras.getInt("idFlashcard");
                idDesk = extras.getInt("idDesk");
                Log.d("name", "onClick: " +idFlashcard +idDesk );

                //displayFlashcardSide(flipFrom);
            }
        }

        //tao đb
        db = new DatabaseApp(this);
        flashcardArrayList = new ArrayList<>();
        flashcardArrayList = db.getAllFlashcardByDeskID(idDesk);
        int position = findPositionById(idFlashcard);
               if (position != -1) {
                   LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                   recyclerFlashcard.setLayoutManager(layoutManager);
                   recyclerFlashcard.scrollToPosition(position);


                }
        Log.d("size", "onCreate: " + flashcardArrayList.size());
        adapter = new FlashcatAdapter(flashcardArrayList,this);
        recyclerFlashcard.setAdapter(adapter);
        //cap nhat vi tri flashcard

        recyclerFlashcard.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition != -1) {
                    txtNumber.setText(String.valueOf(firstVisibleItemPosition+1)+"/" +flashcardArrayList.size());
                }
            }
        });

    }
    private int findPositionById(int idFlashcard) {
        for (int i = 0; i < flashcardArrayList.size(); i++) {
            if (flashcardArrayList.get(i).getID_Flashcard() == idFlashcard) {
                return i;
            }
        }
        return -1;
    }
    private void displayFlashcardSide(String side) {
        if (side != null) {
            db = new DatabaseApp(FlashcardActivity.this);
            Flashcard flashcard = db.getFlashcardByID(idFlashcard);
            if (flashcard != null) {
                if (side.equals("front")) {

                } else if (side.equals("back")) {
                }
            }
        }
    }
    public  void findID(){
        toolbarFlashcard = findViewById(R.id.toolbar_Flashcard);
        recyclerFlashcard = findViewById(R.id.list_item_flashcard);
        btnReturnFlashcard = findViewById(R.id.btn_return_flashcard);
        txtNumber = findViewById(R.id.txt_number_flashcard);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            setResult(RESULT_OK);
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}