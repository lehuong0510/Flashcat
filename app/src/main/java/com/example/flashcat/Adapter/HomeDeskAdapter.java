package com.example.flashcat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcat.Model.Desk;
import com.example.flashcat.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class HomeDeskAdapter extends RecyclerView.Adapter<HomeDeskAdapter.DeskViewHolder>{
    private ArrayList<Desk> deskArrayList;
    private Context context;
    public void setData(ArrayList<Desk> deskArrayList){
        this.deskArrayList = deskArrayList;
        notifyDataSetChanged();
    }
    public HomeDeskAdapter(ArrayList<Desk> deskArrayList, Context context) {
        this.deskArrayList = deskArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_desk_home,parent,false);
        return new DeskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeskViewHolder holder, int position) {
        Desk desk = deskArrayList.get(position);
        if(desk == null)
            return;
        holder.txtDeskName.setText(desk.getName_deck());
        holder.txtNumberCard.setText(desk.getNumber_flashcard());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        LocalDateTime createDay = desk.getCreate_day();
        String formattedDateTime = formatter.format(createDay);
        holder.txtCreateDay.setText(formattedDateTime);
    }

    @Override
    public int getItemCount() {
        if(deskArrayList!=null)
            return deskArrayList.size();
        return 0;
    }

    public class DeskViewHolder extends RecyclerView.ViewHolder{
        private TextView txtDeskName;
        private TextView txtNumberCard;
        private TextView txtCreateDay;
        public DeskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDeskName = itemView.findViewById(R.id.txt_DeskName);
            txtNumberCard = itemView.findViewById(R.id.txt_NumberCard);
            txtCreateDay = itemView.findViewById(R.id.txt_create_day);
        }
    }
}
