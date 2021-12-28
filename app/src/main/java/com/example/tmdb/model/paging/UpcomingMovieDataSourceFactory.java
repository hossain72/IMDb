package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.retrofit.MovieDataService;

public class UpcomingMovieDataSourceFactory extends DataSource.Factory {

    private Application application;
    private MovieDataService movieDataService;
    private MutableLiveData<UpcomingMovieDataSource> dataSourceMutableLiveData;
    private UpcomingMovieDataSource dataSource;

    public UpcomingMovieDataSourceFactory(Application application, MovieDataService movieDataService) {
        this.application = application;
        this.movieDataService = movieDataService;
        dataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new UpcomingMovieDataSource(application, movieDataService);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<UpcomingMovieDataSource> getDataSourceMutableLiveData() {
        return dataSourceMutableLiveData;
    }
}
