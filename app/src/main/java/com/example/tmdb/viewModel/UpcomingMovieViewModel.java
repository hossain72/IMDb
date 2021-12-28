package com.example.tmdb.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.model.UpcomingMovie;
import com.example.tmdb.model.paging.UpcomingMovieDataSourceFactory;
import com.example.tmdb.repository.Repository;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UpcomingMovieViewModel extends AndroidViewModel {

    private Repository repository;
    private UpcomingMovieDataSourceFactory factory;
    private Executor executor;
    private LiveData<PagedList<UpcomingMovie>> upcomingMoviePagedList;

    public UpcomingMovieViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        MovieDataService service = RetrofitInstance.getInstance();
        factory = new UpcomingMovieDataSourceFactory(application, service);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(40)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        upcomingMoviePagedList = new LivePagedListBuilder<Long, UpcomingMovie>(factory, config)
                .setFetchExecutor(executor)
                .build();


    }

    public MutableLiveData<List<UpcomingMovie>> getUpcomingMovie() {
        return repository.getUpcomingMovie();
    }

    public LiveData<PagedList<UpcomingMovie>> getUpcomingMoviePagedList() {
        return upcomingMoviePagedList;
    }
}
