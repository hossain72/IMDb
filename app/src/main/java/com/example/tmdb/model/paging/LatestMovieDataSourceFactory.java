package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.retrofit.MovieDataService;

import java.util.List;

public class LatestMovieDataSourceFactory extends DataSource.Factory {

    private Application application;
    private MovieDataService service;
    private MutableLiveData<LatestMovieDataSource> dataSourceMutableLiveData;
    private LatestMovieDataSource dataSource;

    public LatestMovieDataSourceFactory(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
        dataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new LatestMovieDataSource(application, service);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<LatestMovieDataSource> getDataSourceMutableLiveData() {
        return dataSourceMutableLiveData;
    }
}
