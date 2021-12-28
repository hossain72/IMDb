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
import com.example.tmdb.adapter.TopRatedTVAdapter;
import com.example.tmdb.databinding.FragmentTopRatedTvseriesBinding;
import com.example.tmdb.model.TopRatedTV;
import com.example.tmdb.model.TopRatedTVResponse;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;
import com.example.tmdb.viewModel.MovieViewModel;
import com.example.tmdb.viewModel.TopRatedTvViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedTVSeriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private PagedList<TopRatedTV> topRatedTVPagedList;
    private TopRatedTVAdapter adapter;
    private TopRatedTvViewModel viewModel;
    private FragmentTopRatedTvseriesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated_tvseries, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(TopRatedTvViewModel.class);
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTopRatedTVSeries();
            }
        });

        getTopRatedTVSeries();

        return binding.getRoot();
    }

    private void getTopRatedTVSeries() {
 /*       viewModel.getTopRatedTvSeries().observe(getViewLifecycleOwner(), new Observer<List<TopRatedTV>>() {
            @Override
            public void onChanged(List<TopRatedTV> topRatedTVS) {
                topRatedTVList = topRatedTVS;
                loadTopRatedTV();
            }
        });*/

        viewModel.getTopRatedTvPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<TopRatedTV>>() {
            @Override
            public void onChanged(PagedList<TopRatedTV> topRatedTVS) {
                topRatedTVPagedList = topRatedTVS;
                loadTopRatedTV();
            }
        });

    }

    private void loadTopRatedTV() {

        adapter = new TopRatedTVAdapter(getContext());
        adapter.submitList(topRatedTVPagedList);

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
