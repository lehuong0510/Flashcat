package com.example.flashcat.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.flashcat.Activity.SearchActivity;
import com.example.flashcat.Adapter.HomeDeskAdapter;
import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Database.DeskDatabase;
import com.example.flashcat.Model.Desk;
import com.example.flashcat.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Desk> listDesk;
    private HomeDeskAdapter adapterDesk;
    private RecyclerView recyclerViewDesk;
    private Button btnSearch;
    private TextView txtName;
    private Button btnNotification;
    private TextView btnSeeAll;
    public boolean isVertical = true;
    public DatabaseApp db;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        listDesk = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeString1 = "2024-03-31 15:30:00";
        String dateTimeString2 = "2024-04-01 10:00:00";
        String dateTimeString3 = "2024-04-02 14:45:00";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            db = new DatabaseApp(getContext());
           // db.addDesk(new Desk(1, "Family", false, LocalDateTime.parse(dateTimeString1, formatter), "11", 4));
//            db.addDesk(new Desk(2, "Office", true, LocalDateTime.parse(dateTimeString2, formatter), "12", 3));
            listDesk = db.getAllDesk();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        btnSearch = rootView.findViewById(R.id.btnSearch);
        txtName = rootView.findViewById(R.id.txtName);
        btnSeeAll = rootView.findViewById(R.id.btnSeeAll);
        recyclerViewDesk = rootView.findViewById(R.id.lst_desk);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDesk.setLayoutManager(layoutManager);
        adapterDesk =  new HomeDeskAdapter(listDesk,getContext());
        recyclerViewDesk.setAdapter(adapterDesk);
        recyclerViewDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSeeAll.setPaintFlags(btnSeeAll.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVertical) {
                    LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewDesk.setLayoutManager(horizontalLayoutManager);
                    btnSeeAll.setText("See All");
                } else {
                    LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerViewDesk.setLayoutManager(verticalLayoutManager);
                    btnSeeAll.setText("Collapse");
                }
                isVertical = !isVertical;
                adapterDesk.notifyDataSetChanged();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SearchActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }
}