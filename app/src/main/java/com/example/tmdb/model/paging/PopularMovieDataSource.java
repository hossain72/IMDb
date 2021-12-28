package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.R;
import com.example.tmdb.model.MovieDBResponse;
import com.example.tmdb.model.PopularMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovieDataSource extends PageKeyedDataSource<Long, PopularMovie> {

    private Application application;
    private MovieDataService service;

    public PopularMovieDataSource(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, PopularMovie> callback) {

        service = RetrofitInstance.getInstance();
        Call<MovieDBResponse> call = service.getPopularMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), 1);

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null && movieDBResponse.getPopularMovies() !=null){
                    List<PopularMovie> popularMovieList = movieDBResponse.getPopularMovies();
                    callback.onResult(popularMovieList, null, (long)2);
                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, PopularMovie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, PopularMovie> callback) {

        service = RetrofitInstance.getInstance();
        Call<MovieDBResponse> call = service.getPopularMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null && movieDBResponse.getPopularMovies() !=null){
                    List<PopularMovie> popularMovieList = movieDBResponse.getPopularMovies();
                    callback.onResult(popularMovieList, params.key+1);
                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }
}
