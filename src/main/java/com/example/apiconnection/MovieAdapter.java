package com.example.apiconnection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieItem> movieItems;


    public MovieAdapter(List<MovieItem> movieItems) {
        this.movieItems=movieItems;
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
        holder.tvTitle.setText(movieItems.get(position).getTitle());
        int year = movieItems.get(position).getYear();
        holder.tvYear.setText(String.valueOf(year));
        String imageUrl = movieItems.get(position).getImage().getUrl();
        Picasso.get().load(imageUrl).into(holder.ivImage);

    }



    @Override
    public int getItemCount() {
        return movieItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvYear;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
