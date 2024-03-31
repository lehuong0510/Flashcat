package com.example.flashcat.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import java.util.ArrayList;

public class DeskFlashcardTopAdapter extends RecyclerView.Adapter<DeskFlashcardTopAdapter.DeskTopViewHolder>{
    private ArrayList<Flashcard> flashcardArrayList;
    private Context context;
    private static final int TYPE_FLASHCARD = 1;
    private static final int TYPE_INTERACTIVE_LAYOUT = 2;


    public DeskFlashcardTopAdapter(ArrayList<Flashcard> flashcardArrayList, Context context) {
        this.flashcardArrayList = flashcardArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeskTopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FLASHCARD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_flashcard_top_desk, parent, false);
            return new DeskTopViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desk_create_flashcard, parent, false);
            return new DeskTopViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DeskTopViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FLASHCARD) {
            Flashcard flashcard = flashcardArrayList.get(position);
            if(flashcard == null)
                return;
            holder.txtTerm.setText(flashcard.getTerm());
            holder.txtDefinition.setText(flashcard.getDefinition());
        }
        else{
            holder.btnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DeskFlashcardAdapter", "Button Create clicked!");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(flashcardArrayList!=null){
            return flashcardArrayList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == flashcardArrayList.size()-1)
            return TYPE_INTERACTIVE_LAYOUT;
        else
            return TYPE_FLASHCARD;
    }

    public class DeskTopViewHolder extends RecyclerView.ViewHolder{
        private ImageButton btnZoomFront;
        private ImageButton btnZoomBack;
        private TextView txtTerm;
        private TextView txtDefinition;
        private ImageButton btnCreate;
        public DeskTopViewHolder(@NonNull View itemView) {
            super(itemView);
            btnZoomFront = itemView.findViewById(R.id.btn_zoom_front);
            btnZoomBack = itemView.findViewById(R.id.btn_zoom_back);
            txtTerm = itemView.findViewById(R.id.txt_term_flashcard);
            txtDefinition = itemView.findViewById(R.id.txt_definition_flashcard);
            btnCreate = itemView.findViewById(R.id.btn_create_new);

        }
    }
}
