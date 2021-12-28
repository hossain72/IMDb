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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tmdb.R;
import com.example.tmdb.adapter.UpcomingMovieAdapter;
import com.example.tmdb.databinding.FragmentPopularMoviesBinding;
import com.example.tmdb.databinding.FragmentUpcomingMoviesBinding;
import com.example.tmdb.model.UpcomingDBRespone;
import com.example.tmdb.model.UpcomingMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;
import com.example.tmdb.viewModel.MovieViewModel;
import com.example.tmdb.viewModel.UpcomingMovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingMoviesFragment extends Fragment {

    private PagedList<UpcomingMovie> upcomingMoviePagedList;
    private UpcomingMovieAdapter adapter;
    private UpcomingMovieViewModel upcomingMovieViewModel;
    private FragmentUpcomingMoviesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_movies, container, false);

        upcomingMovieViewModel = ViewModelProviders.of(getActivity()).get(UpcomingMovieViewModel.class);

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUpcomingMovies();
            }
        });

        getUpcomingMovies();

        return binding.getRoot();
    }

    private void getUpcomingMovies() {

/*        movieViewModel.getUpcomingMovie().observe(getViewLifecycleOwner(), new Observer<List<UpcomingMovie>>() {
            @Override
            public void onChanged(List<UpcomingMovie> upcomingMovies) {

                upcomingMovieList = upcomingMovies;
                loadUpcomingMovies();

            }
        });*/

        upcomingMovieViewModel.getUpcomingMoviePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<UpcomingMovie>>() {
            @Override
            public void onChanged(PagedList<UpcomingMovie> upcomingMovies) {
                upcomingMoviePagedList = upcomingMovies;
                loadUpcomingMovies();
            }
        });

    }

    private void loadUpcomingMovies() {

        adapter = new UpcomingMovieAdapter(getContext());
        adapter.submitList(upcomingMoviePagedList);

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
