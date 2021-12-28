package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tmdb.R;
import com.example.tmdb.databinding.ActivityTopRatedMovieBinding;
import com.example.tmdb.model.TopRatedMovie;

public class TopRatedMovieActivity extends AppCompatActivity {

    private ActivityTopRatedMovieBinding binding;
    private TopRatedMovie topRatedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_rated_movie);

        Intent intent = getIntent();

        if (intent.hasExtra("topRatedMovie")){

            topRatedMovie = intent.getParcelableExtra("topRatedMovie");
            getSupportActionBar().setTitle(topRatedMovie.getOriginalTitle());
            binding.setTopRatedMovie(topRatedMovie);

        }

    }
}
