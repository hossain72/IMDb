package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tmdb.R;
import com.example.tmdb.databinding.ActivityTopRatedTvBinding;
import com.example.tmdb.model.TopRatedTV;

public class TopRatedTvActivity extends AppCompatActivity {

    private ActivityTopRatedTvBinding binding;
    private TopRatedTV topRatedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_rated_tv);

        Intent intent = getIntent();
        if (intent.hasExtra("topRatedTV")){

            topRatedTV = intent.getParcelableExtra("topRatedTV");
            getSupportActionBar().setTitle(topRatedTV.getOriginalName());
            binding.setTopRatedTv(topRatedTV);

        }

    }
}
