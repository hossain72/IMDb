package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.retrofit.MovieDataService;

public class PopularTvDataSourceFactory extends DataSource.Factory {

    private Application application;
    private MovieDataService service;
    private MutableLiveData<PopularTvDataSource> mutableLiveData;
    private PopularTvDataSource dataSource;

    public PopularTvDataSourceFactory(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new PopularTvDataSource(application, service);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PopularTvDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
