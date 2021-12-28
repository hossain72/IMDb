package com.example.tmdb.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.model.PopularMovie;
import com.example.tmdb.model.PopularTV;
import com.example.tmdb.model.TopRatedMovie;
import com.example.tmdb.model.TopRatedTV;
import com.example.tmdb.model.UpcomingMovie;
import com.example.tmdb.model.paging.PopularMovieDataSource;
import com.example.tmdb.model.paging.PopularMovieDataSourceFactory;
import com.example.tmdb.repository.Repository;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieViewModel extends AndroidViewModel {

    private Application application;
    private Repository repository;
    private LiveData<PopularMovieDataSource> popularMovieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<PopularMovie>> popularMoviePagedList;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        MovieDataService service = RetrofitInstance.getInstance();
        PopularMovieDataSourceFactory factory = new PopularMovieDataSourceFactory(application, service);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        popularMoviePagedList = new LivePagedListBuilder<Long, PopularMovie>(factory, config)
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<List<PopularMovie>> getPopularMovie() {
        return repository.getPopularMovie();
    }

    public LiveData<PagedList<PopularMovie>> getPopularMoviePagedList() {
        return popularMoviePagedList;
    }
}
