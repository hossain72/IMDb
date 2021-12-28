package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tmdb.R;
import com.example.tmdb.databinding.ActivityPopularTVBinding;
import com.example.tmdb.model.PopularTV;

public class PopularTVActivity extends AppCompatActivity {

    private ActivityPopularTVBinding binding;
    private PopularTV popularTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popular_t_v);

        Intent intent = getIntent();

        if (intent.hasExtra("popularTV")){

            popularTV = intent.getParcelableExtra("popularTV");
            getSupportActionBar().setTitle(popularTV.getName());
            binding.setPopularTV(popularTV);

        }

    }
}
