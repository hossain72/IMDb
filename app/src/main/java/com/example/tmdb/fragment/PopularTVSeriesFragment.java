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
import com.example.tmdb.adapter.PopularTVAdapter;
import com.example.tmdb.databinding.FragmentPopularTvseriesBinding;
import com.example.tmdb.model.PopularTV;
import com.example.tmdb.model.PopularTVResponse;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;
import com.example.tmdb.viewModel.MovieViewModel;
import com.example.tmdb.viewModel.PopularTvViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularTVSeriesFragment extends Fragment {
    private RecyclerView recyclerView;
    private PagedList<PopularTV> popularTVPagedList;
    private PopularTVAdapter adapter;
    private PopularTvViewModel viewModel;
    private FragmentPopularTvseriesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_tvseries, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(PopularTvViewModel.class);
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularTvSeries();
            }
        });

        getPopularTvSeries();

        return binding.getRoot();
    }

    private void getPopularTvSeries() {
  /*      viewModel.getPopularTVSeries().observe(getViewLifecycleOwner(), new Observer<List<PopularTV>>() {
            @Override
            public void onChanged(List<PopularTV> popularTVS) {
                popularTVList = popularTVS;
                loadPopularTVSeries();
            }
        });*/

        viewModel.getPopularTvPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<PopularTV>>() {
            @Override
            public void onChanged(PagedList<PopularTV> popularTVS) {
                popularTVPagedList = popularTVS;
                loadPopularTVSeries();
            }
        });

    }

    private void loadPopularTVSeries() {

        adapter = new PopularTVAdapter(getContext());
        adapter.submitList(popularTVPagedList);

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
