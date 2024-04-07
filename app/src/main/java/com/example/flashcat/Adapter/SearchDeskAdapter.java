package com.example.flashcat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcat.Model.Desk;
import com.example.flashcat.R;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class SearchDeskAdapter extends RecyclerView.Adapter<SearchDeskAdapter.DeskViewHolder> implements Filterable{
    private List<Desk> deskList;
    private List<Desk> deskListOld;

    public SearchDeskAdapter(List<Desk> deskList) {
        this.deskList = deskList;
        this.deskListOld = deskList;
    }
    @NonNull
    @Override
    public DeskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_desk_search,parent,false);
        return new DeskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeskViewHolder holder, int position) {
        Desk desk = deskList.get(position);
        if(desk == null){
            return;
        }
        holder.nameDesk.setText(desk.getName_deck());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime createDay = desk.getCreate_day();
        String formattedDateTime = createDay.format(formatter);
        holder.createdDay.setText(formattedDateTime);
    }

    @Override
    public int getItemCount() {
        if(deskList != null){
            return deskList.size();
        }
        return 0;
    }




    public class DeskViewHolder extends RecyclerView.ViewHolder{
        private TextView nameDesk;
        private TextView createdDay;
        public DeskViewHolder(@NonNull View itemView) {
            super(itemView);
            nameDesk = itemView.findViewById(R.id.txt_nameDesk);
            createdDay = itemView.findViewById(R.id.txt_createdDay);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    deskList = deskListOld;
                } else{
                    List<Desk> list = new ArrayList<>();
                    for(Desk desk : deskListOld){
                        if(desk.getName_deck().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(desk);
                        }
                    }
                    deskList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = deskList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                deskList = (List<Desk>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
