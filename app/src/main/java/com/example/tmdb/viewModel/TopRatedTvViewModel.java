package com.example.tmdb.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.model.TopRatedTV;
import com.example.tmdb.model.paging.TopRatedTvDataSourceFactory;
import com.example.tmdb.repository.Repository;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TopRatedTvViewModel extends AndroidViewModel {

    private Repository repository;
    private Executor executor;
    private LiveData<PagedList<TopRatedTV>> topRatedTvPagedList;

    public TopRatedTvViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        MovieDataService service = RetrofitInstance.getInstance();
        TopRatedTvDataSourceFactory factory = new TopRatedTvDataSourceFactory(application, service);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(3)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        topRatedTvPagedList = new LivePagedListBuilder<Long, TopRatedTV>(factory, config)
                .setFetchExecutor(executor)
                .build();

    }

    public MutableLiveData<List<TopRatedTV>> getTopRatedTvSeries() {
        return repository.getTopRatedTvSeries();
    }

    public LiveData<PagedList<TopRatedTV>> getTopRatedTvPagedList() {
        return topRatedTvPagedList;
    }
}
