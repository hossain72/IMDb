package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.retrofit.MovieDataService;

public class TopRatedTvDataSourceFactory extends DataSource.Factory {

    private Application application;
    private MovieDataService service;
    private TopRatedTvDataSource dataSource;
    private MutableLiveData<TopRatedTvDataSource> mutableLiveData;

    public TopRatedTvDataSourceFactory(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        dataSource = new TopRatedTvDataSource(application, service);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<TopRatedTvDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
