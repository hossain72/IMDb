package com.example.tmdb.view;

import android.content.Intent;
import android.os.Bundle;
import com.example.tmdb.databinding.ActivityShowPopularMovieBinding;
import com.example.tmdb.model.PopularMovie;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import com.example.tmdb.R;

public class ShowPopularMovieActivity extends AppCompatActivity {

    private PopularMovie popularMovie;
    private ActivityShowPopularMovieBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_popular_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        if (intent.hasExtra("popularMovie")) {

            popularMovie = intent.getParcelableExtra("popularMovie");
            getSupportActionBar().setTitle(popularMovie.getOriginalTitle());
            binding.setPopularMovie(popularMovie);

        }

    }

}
