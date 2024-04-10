package com.example.flashcat.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcat.Database.DatabaseApp;
import com.example.flashcat.Model.Word;
import com.example.flashcat.R;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    private Context context;
    private ArrayList<Word> wordArrayList;
    private DatabaseApp db;

    public DictionaryAdapter(Context context, ArrayList<Word> wordArrayList) {
        this.context = context;
        this.wordArrayList = wordArrayList;
        db = new DatabaseApp(context);
    }

    @NonNull
    @Override
    public DictionaryAdapter.DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dictionary,parent,false);
        return new DictionaryViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull DictionaryAdapter.DictionaryViewHolder holder, int position) {
        holder.txtWord.setText(wordArrayList.get(position).getWord());
        holder.txtMinus.setText(wordArrayList.get(position).getMinusWord());
        holder.txtMinus.setText(wordArrayList.get(position).getDefinitionWord());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteWord(wordArrayList.get(position).getId());
                wordArrayList.remove(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return wordArrayList.size();
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder {
        private TextView txtWord;
        private TextView txtMinus;
        private TextView txtDefinition;
        private ImageButton btnDelete;
        private ImageButton btnArrowcard;
        public DictionaryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txt_card);
            txtMinus = itemView.findViewById(R.id.txt_minus_card);
            txtDefinition = itemView.findViewById(R.id.txt_definition_card);
            btnDelete = itemView.findViewById(R.id.btn_delete_card);
            btnArrowcard= itemView.findViewById(R.id.btn_arrow_card);
        }
    }
}
