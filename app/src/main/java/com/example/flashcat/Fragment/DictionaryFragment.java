package com.example.flashcat.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcat.Activity.WordActivity;
import com.example.flashcat.Adapter.DictionaryAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Word;
import com.example.flashcat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DictionaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DictionaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchView  searchWord;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerViewDictionary;
    private DictionaryAdapter dictionaryAdapter;
    private ArrayList<Word> wordArrayList;
    private DatabaseApp db;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DictionaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DictionaryFragment newInstance(String param1, String param2) {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = new DatabaseApp(getContext());
        wordArrayList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dictionary, container, false);
        searchWord = rootView.findViewById(R.id.search_dictionary);
        progressDialog = new ProgressDialog(getContext());
        recyclerViewDictionary = rootView.findViewById(R.id.list_Desk_Search);

        wordArrayList = db.getAllWord();
        //xet layout recycleview
        recyclerViewDictionary.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        dictionaryAdapter = new DictionaryAdapter(getContext(),wordArrayList);
        recyclerViewDictionary.setAdapter(dictionaryAdapter);
        List<String> keywordList = new ArrayList<>();
        searchWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // xu ly su kien khi an enter or button search
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent i = new Intent(getContext(), WordActivity.class);
                i.putExtra("search_query", query);

                progressDialog.setTitle("Loading...");
                progressDialog.show();
                startActivity(i);
                return true;
            }

            //xu ly su kien khi nguoi dung thay doi van ban -> dung cap nhat goi y tim kiem
            @Override
            public boolean onQueryTextChange(String newText) {


                return true;
            }});

        return rootView;
    }

}