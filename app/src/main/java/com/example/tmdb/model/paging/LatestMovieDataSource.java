package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.R;
import com.example.tmdb.model.LatestDBResponse;
import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestMovieDataSource extends PageKeyedDataSource<Long, LatestMovie> {

    private Application application;
    private MovieDataService service;

    public LatestMovieDataSource(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, LatestMovie> callback) {

        service = RetrofitInstance.getInstance();

        Call<LatestDBResponse> call = service.getLatestMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<LatestDBResponse>() {
            @Override
            public void onResponse(Call<LatestDBResponse> call, Response<LatestDBResponse> response) {
                LatestDBResponse latestDBResponse = response.body();

                if (latestDBResponse != null && latestDBResponse.getLatestMovies() != null){

                    List<LatestMovie> latestMovieList = latestDBResponse.getLatestMovies();
                    callback.onResult(latestMovieList, null, (long) 2);

                }

            }

            @Override
            public void onFailure(Call<LatestDBResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, LatestMovie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, LatestMovie> callback) {

        service = RetrofitInstance.getInstance();

        Call<LatestDBResponse> call = service.getLatestMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), params.key);
        call.enqueue(new Callback<LatestDBResponse>() {
            @Override
            public void onResponse(Call<LatestDBResponse> call, Response<LatestDBResponse> response) {
                LatestDBResponse latestDBResponse = response.body();

                if (latestDBResponse != null && latestDBResponse.getLatestMovies() != null){

                    List<LatestMovie> latestMovieList = latestDBResponse.getLatestMovies();
                    callback.onResult(latestMovieList, params.key+1);

                }

            }

            @Override
            public void onFailure(Call<LatestDBResponse> call, Throwable t) {

            }
        });

    }
}
