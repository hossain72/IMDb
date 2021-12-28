package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.R;
import com.example.tmdb.model.TopRatedDBResponse;
import com.example.tmdb.model.TopRatedMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMovieDataSource extends PageKeyedDataSource<Long, TopRatedMovie> {

    private Application application;
    private MovieDataService service;

    public TopRatedMovieDataSource(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, TopRatedMovie> callback) {

        service = RetrofitInstance.getInstance();

        Call<TopRatedDBResponse> call = service.getTopRatedMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), 1);

        call.enqueue(new Callback<TopRatedDBResponse>() {
            @Override
            public void onResponse(Call<TopRatedDBResponse> call, Response<TopRatedDBResponse> response) {
                TopRatedDBResponse topRatedDBResponse = response.body();

                if (topRatedDBResponse != null && topRatedDBResponse.getTopRatedMovies() != null){
                    List<TopRatedMovie> topRatedMovieList = topRatedDBResponse.getTopRatedMovies();
                    callback.onResult(topRatedMovieList, null, (long) 2);
                }

            }

            @Override
            public void onFailure(Call<TopRatedDBResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, TopRatedMovie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, TopRatedMovie> callback) {

        service = RetrofitInstance.getInstance();

        Call<TopRatedDBResponse> call = service.getTopRatedMoviesWithPage(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<TopRatedDBResponse>() {
            @Override
            public void onResponse(Call<TopRatedDBResponse> call, Response<TopRatedDBResponse> response) {
                TopRatedDBResponse topRatedDBResponse = response.body();

                if (topRatedDBResponse != null && topRatedDBResponse.getTopRatedMovies() != null){
                    List<TopRatedMovie> topRatedMovieList = topRatedDBResponse.getTopRatedMovies();
                    callback.onResult(topRatedMovieList, params.key+1);
                }

            }

            @Override
            public void onFailure(Call<TopRatedDBResponse> call, Throwable t) {

            }
        });

    }
}
