package com.example.tmdb.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tmdb.R;
import com.example.tmdb.adapter.MoviePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private MoviePagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);

        fragmentList = new ArrayList<>();
        fragmentList.add(new PopularMoviesFragment());
        fragmentList.add(new UpcomingMoviesFragment());
        fragmentList.add(new LatestMoviesFragment());
        fragmentList.add(new TopRatedMoviesFragment());

        titleList = new ArrayList<>();
        titleList.add("Popular");
        titleList.add("Upcoming");
        titleList.add("Latest");
        titleList.add("Top Rated");

        adapter = new MoviePagerAdapter(fragmentList, titleList, getActivity().getSupportFragmentManager(), 1);
        viewPager.setAdapter(adapter);

        return view;
    }

}
