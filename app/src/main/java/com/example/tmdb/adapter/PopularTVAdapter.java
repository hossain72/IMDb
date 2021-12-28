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
import com.example.tmdb.databinding.ItemListPopularTvBinding;
import com.example.tmdb.model.PopularTV;
import com.example.tmdb.view.PopularTVActivity;

import java.util.List;

public class PopularTVAdapter extends PagedListAdapter<PopularTV, PopularTVAdapter.ViewHolder> {

    private Context context;
    private ItemListPopularTvBinding binding;

    public PopularTVAdapter(Context context) {
        super(PopularTV.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_popular_tv, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PopularTV popularTV = getItem(position);
        holder.binding.setPopularTvSeries(popularTV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PopularTVActivity.class);
                intent.putExtra("popularTV", popularTV);
                context.startActivity(intent);
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListPopularTvBinding binding;

        public ViewHolder(@NonNull ItemListPopularTvBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
