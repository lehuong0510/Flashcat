package com.example.flashcat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import com.example.flashcat.Adapter.HomeDeskAdapter;
import com.example.flashcat.Adapter.SearchDeskAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.R;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rcvDesks;
    private SearchDeskAdapter deskAdapter;
    private SearchView searchView;
    private DatabaseApp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rcvDesks = findViewById(R.id.rcv_Desk_Search);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                deskAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                deskAdapter.getFilter().filter(newText);
                return true;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvDesks.setLayoutManager(linearLayoutManager);

        deskAdapter = new SearchDeskAdapter(getListDesks());
        rcvDesks.setAdapter(deskAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvDesks.addItemDecoration(itemDecoration);
    }

    private List<Desk> getListDesks() {
        List<Desk> list = new ArrayList<>();
        db = new DatabaseApp(this);
        list = db.getAllDesk();

        return list;
    }
}
