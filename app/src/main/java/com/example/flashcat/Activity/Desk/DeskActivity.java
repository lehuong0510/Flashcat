package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.flashcat.Fragment.DeskFragment;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

public class DeskActivity extends AppCompatActivity {
    private Toolbar toolbarDesk ;
    private Button btnStudy;
    private ImageButton btnMore;
    private TextView txtNameDeskSelected;
    private int idDesk;
    private String nameDesk;
    private ArrayList<Flashcard> listCard;
    private ArrayList<Flashcard> listCardSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);
        findID();

        // Thêm Fragment vào framelayout container
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout_desk, new DeskFragment())
                .commit();
        setSupportActionBar(toolbarDesk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Truyen du lieu khi chon desk
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                nameDesk = extras.getString("Name_Desk");
                idDesk = extras.getInt("ID_Desk");
                Bundle b = new Bundle();
                b.putInt("idDesk",idDesk);
                DeskFragment fragment = new DeskFragment();
                fragment.setArguments(b);

                // Thêm fragment vào activity
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_layout_desk, fragment)
                        .commit();
            }
        }
        if (nameDesk != null) {
            txtNameDeskSelected.setText(nameDesk);
        }


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
        txtNameDeskSelected = findViewById(R.id.txt_NameDesk_selected);

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


}