package com.example.tmdb.fragment;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tmdb.R;
import com.example.tmdb.adapter.PopularMovieAdapter;
import com.example.tmdb.databinding.FragmentPopularMoviesBinding;
import com.example.tmdb.model.PopularMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.viewModel.MovieViewModel;

import java.util.List;

public class PopularMoviesFragment extends Fragment {

    private PagedList<PopularMovie> popularMoviePagedList;
    private PopularMovieAdapter adapter;
    private MovieViewModel movieViewModel;
    private FragmentPopularMoviesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false);

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        getPopularMovies();

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

        return binding.getRoot();
    }

    private void getPopularMovies() {
/*
        movieViewModel.getPopularMovie().observe(getViewLifecycleOwner(), new Observer<List<PopularMovie>>() {
            @Override
            public void onChanged(List<PopularMovie> popularMovies) {
                popularMovieList = popularMovies;
                showPopularMovies();
            }
        });*/

        movieViewModel.getPopularMoviePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<PopularMovie>>() {
            @Override
            public void onChanged(PagedList<PopularMovie> popularMovies) {
                popularMoviePagedList = popularMovies;
                showPopularMovies();
            }
        });

    }

    private void showPopularMovies() {

        adapter = new PopularMovieAdapter(getContext());
        adapter.submitList(popularMoviePagedList);

        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.swipeRefreshLayout.setRefreshing(false);

    }

}
