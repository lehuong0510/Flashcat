package com.example.flashcat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcat.Activity.Desk.CreateFlashcardActivity;
import com.example.flashcat.Adapter.MeaningAdapter;
import com.example.flashcat.Adapter.MinusAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Fragment.DictionaryFragment;
import com.example.flashcat.Model.Dictionary.WordItem;
import com.example.flashcat.Model.Word;
import com.example.flashcat.R;
import com.example.flashcat.api.OnFetchDataListener;
import com.example.flashcat.api.RequestManager;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity {

    private ImageButton back;
    private TextView word;
    private RecyclerView minus;
    private MinusAdapter minusAdapter;
    private MeaningAdapter meaningAdapter;
    private RecyclerView meaing;
    private Button btnCreateFlashCard;
    private String searchWord;
    private ProgressDialog progressDialog;
    private DatabaseApp db;
    private ArrayList<Word> wordArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        findID();
        Intent intent = getIntent();
        searchWord = intent.getStringExtra("search_query");
        //database
        db= new DatabaseApp(WordActivity.this);

        wordArrayList = new ArrayList<>();
        //tao loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        //api
        RequestManager manager = new RequestManager(WordActivity.this);
        manager.getWordMeaning(listener,searchWord);
        // su kien button Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();
            }
        });
        //su kien button CreateFC
        btnCreateFlashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordActivity.this, CreateFlashcardActivity.class);
                startActivity(intent);
            }
        });
    }
    public void findID(){
        back = findViewById(R.id.back_word);
        word = findViewById(R.id.txt_word);
        minus = findViewById(R.id.list_item_minus);
        meaing = findViewById(R.id.meaning);
        btnCreateFlashCard = findViewById(R.id.btn_create_word);

    }
    private OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(WordItem wordItem, String message) {
            if(wordItem==null){
                Toast.makeText(WordActivity.this, "no data found!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(wordItem!=null){
                showData(wordItem);
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(WordActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(WordItem wordItem) {
        word.setText(wordItem.getWord());
        minus.setHasFixedSize(true);
        minus.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        minusAdapter = new MinusAdapter(wordItem.getPhonetic(),WordActivity.this);
        minus.setAdapter(minusAdapter);
        meaing.setHasFixedSize(true);
        meaing.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        meaningAdapter = new MeaningAdapter(WordActivity.this,wordItem.getMeanings());
        meaing.setAdapter(meaningAdapter);
        //add data
        db.addWord(new Word(1,
                wordItem.getWord(),
                wordItem.getPhonetic().get(0).getText(),
                String.valueOf(wordItem.getMeanings().get(0).getDefinitions().get(0).getDefinition())));

    }


}