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
import com.example.tmdb.adapter.TopRatedMoviesAdapter;
import com.example.tmdb.databinding.FragmentTopRatedMoviesBinding;
import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.model.TopRatedDBResponse;
import com.example.tmdb.model.TopRatedMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;
import com.example.tmdb.viewModel.MovieViewModel;
import com.example.tmdb.viewModel.TopRatedMovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMoviesFragment extends Fragment {

    private MovieDataService movieDataService;
    private RecyclerView recyclerView;
    private PagedList<TopRatedMovie> topRatedMoviePagedList;
    private TopRatedMoviesAdapter adapter;
    private TopRatedMovieViewModel viewModel;
    private FragmentTopRatedMoviesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated_movies, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(TopRatedMovieViewModel.class);
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTopRatedMovies();
            }
        });

        getTopRatedMovies();

        return  binding.getRoot();
    }

    private void getTopRatedMovies() {
  /*      viewModel.getTopRatedMovie().observe(getViewLifecycleOwner(), new Observer<List<TopRatedMovie>>() {
            @Override
            public void onChanged(List<TopRatedMovie> topRatedMovies) {
                topRatedMovieList = topRatedMovies;
                loadTopRatedMovies();
            }
        });*/

        viewModel.getTopRatedMoviePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<TopRatedMovie>>() {
            @Override
            public void onChanged(PagedList<TopRatedMovie> topRatedMovies) {
                topRatedMoviePagedList = topRatedMovies;
                loadTopRatedMovies();
            }
        });

    }

    private void loadTopRatedMovies() {

        adapter = new TopRatedMoviesAdapter(getContext());
        adapter.submitList(topRatedMoviePagedList);

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
