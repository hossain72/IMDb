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
import com.example.tmdb.databinding.ItemListTopRatedTvBinding;
import com.example.tmdb.model.TopRatedTV;
import com.example.tmdb.view.TopRatedTvActivity;

import java.util.List;

public class TopRatedTVAdapter extends PagedListAdapter<TopRatedTV, TopRatedTVAdapter.ViewHolder> {

    private Context context;
    private ItemListTopRatedTvBinding binding;

    public TopRatedTVAdapter(Context context) {
        super(TopRatedTV.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_top_rated_tv, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final TopRatedTV topRatedTV = getItem(position);
        holder.binding.setTopRatedTV(topRatedTV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TopRatedTvActivity.class);
                intent.putExtra("topRatedTV", topRatedTV);
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListTopRatedTvBinding binding;

        public ViewHolder(@NonNull ItemListTopRatedTvBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
