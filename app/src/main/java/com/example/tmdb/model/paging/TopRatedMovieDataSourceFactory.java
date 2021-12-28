package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.retrofit.MovieDataService;

public class TopRatedMovieDataSourceFactory extends DataSource.Factory {

    private Application application;
    private MovieDataService service;
    private TopRatedMovieDataSource dataSource;
    private MutableLiveData<TopRatedMovieDataSource> dataSourceMutableLiveData;

    public TopRatedMovieDataSourceFactory(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
        dataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new TopRatedMovieDataSource(application, service);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<TopRatedMovieDataSource> getDataSourceMutableLiveData() {
        return dataSourceMutableLiveData;
    }
}
