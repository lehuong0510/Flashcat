package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.flashcat.Adapter.DeskFlashcatAdapter;
import com.example.flashcat.Fragment.DeskFragment;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import java.util.ArrayList;

public class DeskActivity extends AppCompatActivity {
    private Toolbar toolbarDesk ;
    private Button btnStudy;
    private ImageButton btnMore;
    private RecyclerView rcFlashcard;
    private ArrayList<Flashcard> lstFlashCard;
    private DeskFlashcatAdapter adapter;
    private DeskFragment deskFragment = new DeskFragment();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);
        findID();
        // Thêm Fragment vào framelayout container
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout_desk, deskFragment)
                .commit();



        setSupportActionBar(toolbarDesk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstFlashCard = new ArrayList<>();
        lstFlashCard.add(new Flashcard("one","mot"));
        lstFlashCard.add(new Flashcard("two","hai"));
        lstFlashCard.add(new Flashcard("three","ba"));
        lstFlashCard.add(new Flashcard("four","bon"));

        adapter = new DeskFlashcatAdapter(lstFlashCard,this);
        rcFlashcard.setAdapter(adapter);
        rcFlashcard.setLayoutManager(new LinearLayoutManager(this));
        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeskActivity.this, FlashcardActivity.class);
                startActivity(i);
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });
    }
    public void findID(){
        toolbarDesk = findViewById(R.id.toolbar_Desk);
        btnStudy = findViewById(R.id.btn_Study);
        btnMore = findViewById(R.id.action_more_desk);
        rcFlashcard= findViewById(R.id.list_flashcard_desk);
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
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_more_desk));
        popupMenu.getMenuInflater().inflate(R.menu.menu_desk, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện
                int it= item.getItemId();
                if(it == R.id.menu_Edit){
                    Intent i = new Intent(DeskActivity.this, EditDeskActivity.class);
                    startActivity(i);
                }
                else if(it == R.id.menu_Delete){

                }
                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Xử lý khi Intent đã trở về từ FlashcardActivity
                deskFragment.getFlashcardTopAdapter().setStatus(true); // Đặt status thành true khi Intent trở về
            }
        }

    }
}