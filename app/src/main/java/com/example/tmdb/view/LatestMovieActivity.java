package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tmdb.R;
import com.example.tmdb.databinding.ActivityLatestMovieBinding;
import com.example.tmdb.model.LatestMovie;

public class LatestMovieActivity extends AppCompatActivity {

    private ActivityLatestMovieBinding binding;
    private LatestMovie latestMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_latest_movie);

        Intent intent = getIntent();

        if (intent.hasExtra("latestMovie")){

            latestMovie = intent.getParcelableExtra("latestMovie");
            getSupportActionBar().setTitle(latestMovie.getOriginalTitle());
            binding.setLatestMovie(latestMovie);

        }

    }
}
