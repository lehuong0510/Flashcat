package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.flashcat.Activity.WordActivity;
import com.example.flashcat.Adapter.MeaningAdapter;
import com.example.flashcat.Adapter.MinusAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Dictionary.Phonetics;
import com.example.flashcat.Model.Dictionary.WordItem;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.Model.Word;
import com.example.flashcat.R;
import com.example.flashcat.api.OnFetchDataListener;
import com.example.flashcat.api.RequestManager;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class CreateFlashcardActivity extends AppCompatActivity {
    private DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ImageButton btnBack;
    private Spinner spSelectDesk;
    private EditText edFront;
    private EditText edBack;
    private Button btnDoneNewFlashcard;
    private ArrayList<String> deskNames;
    public DatabaseApp db;
    private ArrayList<Desk> desks;
    int selectedDeskId;
    String selectedDeskName;
    String example="No example";
    String sound ="No sound";
    String s;
    int idDesk;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_create_flashcard);
        findID();

        //nhan data tu word
        Intent intent = getIntent();
        if(intent!=null)
        {
            String w= intent.getStringExtra("word_FC");
            String d= intent.getStringExtra("definition_FC");
            String e= intent.getStringExtra("example_FC");
            idDesk = intent.getIntExtra("id_desk",1);
            Log.d("de", "onCreate: " + idDesk);
            if(e!=null){
                example= e;
            }
            s = intent.getStringExtra("sound_FC");


            edFront.setText(w);
            edBack.setText(d);
        }
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
        //Set vi tri desk
        // Tìm vị trí của desk có ID là idDesk trong danh sách desks
        int defaultDeskPosition = -1; // Khởi tạo vị trí mặc định là -1 (không tìm thấy)
        for (int i = 0; i < desks.size(); i++) {
            if (desks.get(i).getID_Deck() == idDesk) {
                defaultDeskPosition = i;
                break;
            }
        }
        // Nếu tìm thấy vị trí hợp lệ, set vị trí mặc định cho Spinner
        if (defaultDeskPosition != -1) {
            spSelectDesk.setSelection(defaultDeskPosition);
            adapter.notifyDataSetChanged();
        }
        else {
            spSelectDesk.setSelection(1);
            adapter.notifyDataSetChanged();
        }

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
                if(s!=null){
                    sound= s;
                    saveFlashcard();
                }
                else
                {
                    RequestManager manager = new RequestManager(CreateFlashcardActivity.this);
                    manager.getWordMeaning(listener,edFront.getText().toString());

                }
                Intent i = new Intent(CreateFlashcardActivity.this, DeskActivity.class);
                i.putExtra("ID_Desk", selectedDeskId);
                i.putExtra("Name_Desk", selectedDeskName);
                startActivity(i);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //event toolbar


    public void findID(){
        btnBack = findViewById(R.id.back_creteFlashcard);
        spSelectDesk = findViewById(R.id.sp_select_desk);
        edFront = findViewById(R.id.ed_front_flashcard);
        edBack = findViewById(R.id.ed_back_flashcard);
        btnDoneNewFlashcard = findViewById(R.id.action_done_new_flashcard);

    }

    private OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(WordItem wordItem, String message) {
            if (wordItem == null) {
                //Toast.makeText(WordActivity.this, "no data found!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (wordItem != null) {
                ArrayList<Phonetics> p = new ArrayList<>();
                p = wordItem.getPhonetic();
                for (Phonetics item : p) {
                    if (item.getAudio().length() > 0) {
                        sound = item.getAudio();
                        Log.d("sound1", "onFetchData: " + sound);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            saveFlashcard();
                        }
                        break;
                    }
                }
            }

        }

        @Override
        public void onFetchData(Desk desk, int message) {

        }

        @Override
        public void onError(String message) {
            //Toast.makeText(WordActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFetchDataList(List<Desk> listDesk) {

        }
    };
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveFlashcard() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String currentDateTimeString = currentDateTime.format(DATE_TIME_FORMATTER);

        // Lưu flashcard với giá trị âm thanh đã nhận được từ API
        db.addFlashcard(new Flashcard(1, edFront.getText().toString(), edBack.getText().toString(), example, sound, false, LocalDateTime.parse(currentDateTimeString, DATE_TIME_FORMATTER), selectedDeskId));

        // Chuyển đến DeskActivity
        Intent i = new Intent(CreateFlashcardActivity.this, DeskActivity.class);
        i.putExtra("ID_Desk", selectedDeskId);
        i.putExtra("Name_Desk", selectedDeskName);
        startActivity(i);
    }
}