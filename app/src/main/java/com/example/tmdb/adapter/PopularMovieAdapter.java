package com.example.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.R;
import com.example.tmdb.databinding.ItemListMovieBinding;
import com.example.tmdb.model.PopularMovie;
import com.example.tmdb.view.ShowPopularMovieActivity;

import java.util.List;

public class PopularMovieAdapter extends PagedListAdapter<PopularMovie, PopularMovieAdapter.ViewHolder> {

    private ItemListMovieBinding binding;
    private Context context;

    public PopularMovieAdapter(Context context) {
        super(PopularMovie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_movie, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PopularMovie popularMovie = getItem(position);
        holder.binding.setPopularMovie(popularMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ShowPopularMovieActivity.class);
                intent.putExtra("popularMovie", popularMovie);
                context.startActivity(intent);

            }
        });


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListMovieBinding binding;

        public ViewHolder(@NonNull ItemListMovieBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }

}
