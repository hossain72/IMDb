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
import com.example.tmdb.databinding.ItemListMovieBinding;
import com.example.tmdb.databinding.ItemListUpcomingMovieBinding;
import com.example.tmdb.model.UpcomingMovie;
import com.example.tmdb.view.ShowUpcomingActivity;

import java.util.List;

public class UpcomingMovieAdapter extends PagedListAdapter<UpcomingMovie, UpcomingMovieAdapter.ViewHolder> {

    private Context context;
    private ItemListUpcomingMovieBinding binding;

    public UpcomingMovieAdapter(Context context) {
        super(UpcomingMovie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_upcoming_movie, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UpcomingMovie upcomingMovie = getItem(position);

        holder.binding.setUpcomingMovie(upcomingMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowUpcomingActivity.class);
                intent.putExtra("upcomingMovie", upcomingMovie);
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListUpcomingMovieBinding binding;

        public ViewHolder(@NonNull ItemListUpcomingMovieBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
