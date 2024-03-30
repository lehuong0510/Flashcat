package com.example.flashcat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Flashcard;
import com.example.flashcat.R;

import java.util.ArrayList;

public class DeskFlashcatAdapter extends RecyclerView.Adapter<DeskFlashcatAdapter.DeskFlashcatViewHolder>{
    private ArrayList<Flashcard> flashcardArrayList;
    private Context context;
    public void setData(ArrayList<Flashcard> flashcardArrayList){
        this.flashcardArrayList = flashcardArrayList;
        notifyDataSetChanged();
    }
    public DeskFlashcatAdapter(ArrayList<Flashcard> flashcardArrayList, Context context) {
        this.flashcardArrayList = flashcardArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeskFlashcatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_flashcard_desk,parent,false);
        return new DeskFlashcatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeskFlashcatViewHolder holder, int position) {
        Flashcard flashcard = flashcardArrayList.get(position);
        if(flashcard == null)
            return;
        holder.txtDefinitionDesk.setText(flashcard.getDefinition());
        holder.txtTearmDesk.setText(flashcard.getTerm());
    }

    @Override
    public int getItemCount() {
        if(flashcardArrayList != null){
            return flashcardArrayList.size();
        }
        return 0;
    }

    public class DeskFlashcatViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTearmDesk;
        private TextView txtDefinitionDesk;
        private ImageButton btnVolumeDesk;
        private ImageButton btnStarDesk;
        public DeskFlashcatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTearmDesk = itemView.findViewById(R.id.txt_term_desk);
            txtDefinitionDesk = itemView.findViewById(R.id.txt_definition_desk);
            btnVolumeDesk = itemView.findViewById(R.id.btn_volume_term_desk);
            btnStarDesk = itemView.findViewById(R.id.btn_star_desk);
        }
    }
}
