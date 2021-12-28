package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.databinding.ActivityShowPopularMovieBinding;
import com.example.tmdb.databinding.ActivityShowUpcomingBinding;
import com.example.tmdb.model.UpcomingMovie;

public class ShowUpcomingActivity extends AppCompatActivity {

    private UpcomingMovie upcomingMovie;
    private ActivityShowUpcomingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_upcoming);

        Intent intent = getIntent();
        if (intent.hasExtra("upcomingMovie")) {

            upcomingMovie = intent.getParcelableExtra("upcomingMovie");
            getSupportActionBar().setTitle(upcomingMovie.getTitle());

            binding.setUpcomingMovie(upcomingMovie);

        }

    }
}
