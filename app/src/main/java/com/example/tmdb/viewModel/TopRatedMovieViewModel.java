package com.example.tmdb.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.model.TopRatedMovie;
import com.example.tmdb.model.paging.TopRatedMovieDataSourceFactory;
import com.example.tmdb.repository.Repository;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TopRatedMovieViewModel extends AndroidViewModel {

    private Repository repository;
    private MovieDataService service;
    private Executor executor;
    private LiveData<PagedList<TopRatedMovie>> topRatedMoviePagedList;

    public TopRatedMovieViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        service = RetrofitInstance.getInstance();
        TopRatedMovieDataSourceFactory factory = new TopRatedMovieDataSourceFactory(application, service);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(40)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        topRatedMoviePagedList = new LivePagedListBuilder<Long, TopRatedMovie>(factory, config)
                .setFetchExecutor(executor)
                .build();

    }

    public MutableLiveData<List<TopRatedMovie>> getTopRatedMovie() {
        return repository.getTopRatedMovie();
    }

    public LiveData<PagedList<TopRatedMovie>> getTopRatedMoviePagedList() {
        return topRatedMoviePagedList;
    }
}
