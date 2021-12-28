package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.R;
import com.example.tmdb.model.UpcomingDBRespone;
import com.example.tmdb.model.UpcomingMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingMovieDataSource extends PageKeyedDataSource<Long, UpcomingMovie> {

    private Application application;
    private MovieDataService service;

    public UpcomingMovieDataSource(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, UpcomingMovie> callback) {

        service = RetrofitInstance.getInstance();
        Call<UpcomingDBRespone> call = service.getUpcomingMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), 1);

        call.enqueue(new Callback<UpcomingDBRespone>() {
            @Override
            public void onResponse(Call<UpcomingDBRespone> call, Response<UpcomingDBRespone> response) {
                UpcomingDBRespone upcomingDBRespone = response.body();

                if (upcomingDBRespone != null && upcomingDBRespone.getUpcomingMovies() != null){

                    List<UpcomingMovie> upcomingMovieList = upcomingDBRespone.getUpcomingMovies();
                    callback.onResult(upcomingMovieList, null, (long)2);

                }
            }

            @Override
            public void onFailure(Call<UpcomingDBRespone> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, UpcomingMovie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, UpcomingMovie> callback) {

        service = RetrofitInstance.getInstance();
        Call<UpcomingDBRespone> call = service.getUpcomingMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<UpcomingDBRespone>() {
            @Override
            public void onResponse(Call<UpcomingDBRespone> call, Response<UpcomingDBRespone> response) {
                UpcomingDBRespone upcomingDBRespone = response.body();

                if (upcomingDBRespone != null && upcomingDBRespone.getUpcomingMovies() != null){

                    List<UpcomingMovie> upcomingMovieList = upcomingDBRespone.getUpcomingMovies();
                    callback.onResult(upcomingMovieList, params.key+1);

                }
            }

            @Override
            public void onFailure(Call<UpcomingDBRespone> call, Throwable t) {

            }
        });

    }
}
