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
import com.example.tmdb.databinding.ItemListTopRatedMovieBinding;
import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.model.TopRatedMovie;
import com.example.tmdb.view.TopRatedMovieActivity;

import java.util.List;

public class TopRatedMoviesAdapter extends PagedListAdapter<TopRatedMovie, TopRatedMoviesAdapter.ViewHolder> {

    private Context context;
    private ItemListTopRatedMovieBinding binding;

    public TopRatedMoviesAdapter(Context context) {
        super(TopRatedMovie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_top_rated_movie, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final TopRatedMovie topRatedMovie = getItem(position);
        holder.binding.setTopRatedMovie(topRatedMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TopRatedMovieActivity.class);
                intent.putExtra("topRatedMovie", topRatedMovie);
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListTopRatedMovieBinding binding;

        public ViewHolder(@NonNull ItemListTopRatedMovieBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
