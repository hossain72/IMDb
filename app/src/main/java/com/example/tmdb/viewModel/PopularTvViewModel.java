package com.example.tmdb.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.model.PopularTV;
import com.example.tmdb.model.paging.PopularMovieDataSourceFactory;
import com.example.tmdb.model.paging.PopularTvDataSourceFactory;
import com.example.tmdb.repository.Repository;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PopularTvViewModel extends AndroidViewModel {

    private Repository repository;
    private Executor executor;
    private LiveData<PagedList<PopularTV>> popularTvPagedList;

    public PopularTvViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        MovieDataService service = RetrofitInstance.getInstance();
        PopularTvDataSourceFactory factory = new PopularTvDataSourceFactory(application, service);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(40)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        popularTvPagedList = new LivePagedListBuilder<Long, PopularTV>(factory, config)
                .setFetchExecutor(executor)
                .build();

    }


    public MutableLiveData<List<PopularTV>> getPopularTVSeries() {
        return repository.getPopularTVSeries();
    }

    public LiveData<PagedList<PopularTV>> getPopularTvPagedList() {
        return popularTvPagedList;
    }
}
