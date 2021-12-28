package com.example.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.databinding.ItemListLatestMovieBinding;
import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.model.PopularMovie;
import com.example.tmdb.view.LatestMovieActivity;

import java.util.List;

public class LatestMovieAdapter extends PagedListAdapter<LatestMovie, LatestMovieAdapter.ViewHolder> {

    private Context context;
    private ItemListLatestMovieBinding binding;

    public LatestMovieAdapter(Context context) {
        super(LatestMovie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_latest_movie, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final LatestMovie latestMovie = getItem(position);

        holder.binding.setLatestMovie(latestMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LatestMovieActivity.class);
                intent.putExtra("latestMovie", latestMovie);
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListLatestMovieBinding binding;

        public ViewHolder(@NonNull ItemListLatestMovieBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
