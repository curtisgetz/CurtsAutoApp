package com.curtisgetz.curtsautoapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curtisgetz.curtsautoapp.R;
import com.curtisgetz.curtsautoapp.model.Genre;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;



public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> genres = new ArrayList<>();
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;
    private int lastSelectedPos;


    public GenreAdapter(ItemClickListener itemClickListener, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<Genre> genres){
        this.genres.clear();
        this.genres = genres;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.genre_list_item, parent, false);;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String genreName = genres.get(position).getName();
        holder.genreNameTV.setText(genreName);
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public String getItem(int pos) {
        return genres.get(pos).getId();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView genreNameTV;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            genreNameTV = itemView.findViewById(R.id.genre_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int pos);
    }

}
