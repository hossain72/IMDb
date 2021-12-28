package com.example.tmdb.fragment;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.tmdb.adapter.LatestMovieAdapter;
import com.example.tmdb.databinding.FragmentLatestMoviesBinding;
import com.example.tmdb.model.LatestDBResponse;
import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;
import com.example.tmdb.viewModel.LatestMovieViewModel;
import com.example.tmdb.viewModel.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestMoviesFragment extends Fragment {

    private LatestMovieAdapter adapter;
    private PagedList<LatestMovie> latestMoviePagedList;
    private LatestMovieViewModel latestMovieViewModel;
    private FragmentLatestMoviesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_latest_movies, container, false);

        latestMovieViewModel = ViewModelProviders.of(getActivity()).get(LatestMovieViewModel.class);

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLatestMovies();
            }
        });

        getLatestMovies();

        return binding.getRoot();
    }

    private void getLatestMovies() {
/*        movieViewModel.getLatestMovie().observe(getViewLifecycleOwner(), new Observer<List<LatestMovie>>() {
            @Override
            public void onChanged(List<LatestMovie> latestMovies) {
                latestMovieList = latestMovies;
                loadLatestMovie();
            }
        });*/

        latestMovieViewModel.getLatestMoviePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<LatestMovie>>() {
            @Override
            public void onChanged(PagedList<LatestMovie> latestMovies) {
                latestMoviePagedList = latestMovies;
                loadLatestMovie();
            }
        });

    }

    private void loadLatestMovie() {

        adapter = new LatestMovieAdapter(getContext());
        adapter.submitList(latestMoviePagedList);

        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }else {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.swipeRefreshLayout.setRefreshing(false);

    }

}
