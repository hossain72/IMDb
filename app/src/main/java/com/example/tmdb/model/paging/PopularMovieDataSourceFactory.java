package com.example.tmdb.model.paging;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.retrofit.MovieDataService;

public class PopularMovieDataSourceFactory extends DataSource.Factory {

    private Application application;
    private MovieDataService service;
    private PopularMovieDataSource dataSource;
    private MutableLiveData<PopularMovieDataSource> dataSourceMutableLiveData;

    public PopularMovieDataSourceFactory(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
        dataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        dataSource = new PopularMovieDataSource(application, service);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PopularMovieDataSource> getDataSourceMutableLiveData() {
        return dataSourceMutableLiveData;
    }
}
