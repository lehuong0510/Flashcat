package com.example.flashcat.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.flashcat.Adapter.DeskFlashcardTopAdapter;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeskFragment extends Fragment {
    private RecyclerView rvFlashcard;
    private ImageButton btnCreateNew;
    private ArrayList<Flashcard> listFlashCard;
    private DeskFlashcardTopAdapter flashcardTopAdapter;
    private int totalPages;
    private int currentPage = 0;
    private LinearLayout dotLayout;
    public DeskFlashcardTopAdapter getFlashcardTopAdapter() {
        return flashcardTopAdapter;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeskFragment newInstance(String param1, String param2) {
        DeskFragment fragment = new DeskFragment();
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
        listFlashCard = new ArrayList<>();

        // Thêm phần tử đặc biệt để đại diện cho layout tương tác
        listFlashCard.add(new Flashcard()); // Phần tử đặc biệt
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_desk, container, false);
        rvFlashcard = rootview.findViewById(R.id.lst_flashcard);
        dotLayout = rootview.findViewById(R.id.dot_layout);
        //btnCreateNew = rootview.findViewById(R.id.btn_create_new);

        flashcardTopAdapter = new DeskFlashcardTopAdapter(listFlashCard,getContext());
        rvFlashcard.setAdapter(flashcardTopAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFlashcard.setLayoutManager(layoutManager);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvFlashcard);
        
        totalPages = flashcardTopAdapter.getItemCount();
        // Tạo hình tròn tương ứng với số lượng trang của RecyclerView


        rvFlashcard.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Tính toán và cập nhật trang hiện tại
                currentPage = layoutManager.findFirstVisibleItemPosition();
                updateDots();
            }
        });
        createDots();
        return rootview;

    }

    private void createDots() {
        for (int i = 0; i < totalPages; i++) {
            View dot = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20); // Kích thước của hình tròn
            params.setMargins(5, 0, 5, 0); // Khoảng cách giữa các hình tròn
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.dot_normal); // Tạo một hình tròn mặc định
            dotLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            dotLayout.addView(dot); // Thêm hình tròn vào LinearLayout
        }
        // Bôi đậm hình tròn đầu tiên để đại diện cho trang đầu tiên
        if (dotLayout.getChildCount() > 0) {
            dotLayout.getChildAt(0).setBackgroundResource(R.drawable.dot_selected);
        }
    }
    private void updateDots() {
        for (int i = 0; i < totalPages; i++) {
            if (i == currentPage) {
                dotLayout.getChildAt(i).setBackgroundResource(R.drawable.dot_selected);
            } else {
                dotLayout.getChildAt(i).setBackgroundResource(R.drawable.dot_normal);
            }
        }
    }
}