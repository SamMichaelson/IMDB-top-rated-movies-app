package com.example.apiconnection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IdAdapter extends RecyclerView.Adapter<IdAdapter.ViewHolder> {

    private List<id> idAndChartRatingList;

    public IdAdapter(List<id> idAndChartRatingList) {
        this.idAndChartRatingList = idAndChartRatingList;
    }
    public void addId(id id) {
        idAndChartRatingList.add(id);
        notifyItemInserted(idAndChartRatingList.size() - 1);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvChartRating.setText(idAndChartRatingList.get(position).getChartRating());
        holder.tvId.setText(idAndChartRatingList.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return idAndChartRatingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChartRating;
        TextView tvId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvChartRating = itemView.findViewById(R.id.tvChartRating);
//            tvId          = itemView.findViewById(R.id.tvId);
        }
    }
}
