package com.example.flashcat.Activity.Desk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.flashcat.Activity.HomeActivity;
import com.example.flashcat.Adapter.DeskFlashcatAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Fragment.DeskFragment;
import com.example.flashcat.Fragment.HomeFragment;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

public class DeskActivity extends AppCompatActivity {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Toolbar toolbarDesk ;
    private Button btnStudy;
    private ImageButton btnMore;
    private TextView txtNameDeskSelected;
    private int idDesk;
    private String nameDesk;
    private String nameDeskCreate;
    private String createdDay;

    private ArrayList<Flashcard> listCard;
    private ArrayList<Flashcard> listCardSelected;
    private DatabaseApp db;
    private DeskFlashcatAdapter deskFlashcatAdapter;
    private RecyclerView rcflashcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);
        findID();
        db= new DatabaseApp(DeskActivity.this);
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
                nameDeskCreate = extras.getString("NameDesk");
                idDesk = extras.getInt("ID_Desk");
                createdDay = extras.getString("CreatedDay");
                Log.d("name", "onClick: " +nameDesk );
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
        listCard = new ArrayList<Flashcard>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listCard = db.getAllContactDesk(idDesk);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcflashcard.setLayoutManager(layoutManager);
        deskFlashcatAdapter = new DeskFlashcatAdapter(listCard,this);
        rcflashcard.setAdapter(deskFlashcatAdapter);

        if (nameDesk != null) {
            txtNameDeskSelected.setText(nameDesk);
        }
        if(nameDeskCreate!=null)
        {
            txtNameDeskSelected.setText(nameDeskCreate);
            db.addDesk(new Desk(1,nameDeskCreate,false,LocalDateTime.parse(createdDay,formatter),"12",0));
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
        rcflashcard = findViewById(R.id.list_flashcard_desk);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it = item.getItemId();
        if(it == android.R.id.home){
            Intent intent = new Intent(DeskActivity.this, HomeActivity.class);

            startActivity(intent);
            finish();

            return true; // Xử lý sự kiện thành công
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
                    i.putExtra("ID_Desk",idDesk);
                    i.putExtra("NameDesk", nameDesk);
                    startActivityForResult(i,200);
                }
                else if(it == R.id.menu_Delete){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DeskActivity.this);
                    builder.setMessage("Are you sure you want to delete this item?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(DeskActivity.this, HomeActivity.class);
                                    db.deleteDesk(idDesk);

                                    startActivity(intent);
                                    finish();

                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 210)
        {
            if (data != null) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    nameDesk = extras.getString("NameDesk");
                    //nameDeskCreate = extras.getString("NameDesk");
                    idDesk = extras.getInt("ID_Desk");
                    createdDay = extras.getString("CreatedDay");
                    Log.d("name", "onClick: " +nameDesk );
                    Bundle b = new Bundle();
                    b.putInt("idDesk",idDesk);
                    DeskFragment fragment = new DeskFragment();
                    fragment.setArguments(b);

                    // Thêm fragment vào activity
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout_desk, fragment)
                            .commit();
                    listCard = new ArrayList<Flashcard>();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        listCard = db.getAllContactDesk(idDesk);
                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    rcflashcard.setLayoutManager(layoutManager);
                    deskFlashcatAdapter = new DeskFlashcatAdapter(listCard,this);
                    rcflashcard.setAdapter(deskFlashcatAdapter);
                }
            }

        }
    }
}