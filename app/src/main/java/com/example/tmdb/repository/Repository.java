package com.example.tmdb.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.tmdb.R;
import com.example.tmdb.adapter.PopularMovieAdapter;
import com.example.tmdb.model.LatestDBResponse;
import com.example.tmdb.model.LatestMovie;
import com.example.tmdb.model.MovieDBResponse;
import com.example.tmdb.model.PopularMovie;
import com.example.tmdb.model.PopularTV;
import com.example.tmdb.model.PopularTVResponse;
import com.example.tmdb.model.TopRatedDBResponse;
import com.example.tmdb.model.TopRatedMovie;
import com.example.tmdb.model.TopRatedTV;
import com.example.tmdb.model.TopRatedTVResponse;
import com.example.tmdb.model.UpcomingDBRespone;
import com.example.tmdb.model.UpcomingMovie;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Application application;
    private MovieDataService service;

    public Repository(Application application) {
        this.application = application;
        service = RetrofitInstance.getInstance();
    }

    public MutableLiveData<List<PopularMovie>> getPopularMovie() {

        final MutableLiveData<List<PopularMovie>> mutableLiveData = new MutableLiveData<>();

        Call<MovieDBResponse> call = service.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null && movieDBResponse.getPopularMovies() != null) {

                    List<PopularMovie> popularMovieList = movieDBResponse.getPopularMovies();
                    mutableLiveData.setValue(popularMovieList);

                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }


    public MutableLiveData<List<UpcomingMovie>> getUpcomingMovie() {

        final MutableLiveData<List<UpcomingMovie>> mutableLiveData = new MutableLiveData<>();

        Call<UpcomingDBRespone> call = service.getUpcomingMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<UpcomingDBRespone>() {
            @Override
            public void onResponse(Call<UpcomingDBRespone> call, Response<UpcomingDBRespone> response) {
                UpcomingDBRespone upcomingDBRespone = response.body();

                if (upcomingDBRespone != null && upcomingDBRespone.getUpcomingMovies() != null) {

                    List<UpcomingMovie> upcomingMovieList = upcomingDBRespone.getUpcomingMovies();
                    mutableLiveData.setValue(upcomingMovieList);

                }
            }

            @Override
            public void onFailure(Call<UpcomingDBRespone> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<LatestMovie>> getLatestMovie() {

        final MutableLiveData<List<LatestMovie>> mutableLiveData = new MutableLiveData<>();

        Call<LatestDBResponse> call = service.getLatestMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<LatestDBResponse>() {
            @Override
            public void onResponse(Call<LatestDBResponse> call, Response<LatestDBResponse> response) {
                LatestDBResponse latestDBResponse = response.body();

                if (latestDBResponse != null && latestDBResponse.getLatestMovies() != null) {

                    List<LatestMovie> latestMovieList = latestDBResponse.getLatestMovies();
                    mutableLiveData.setValue(latestMovieList);

                }
            }

            @Override
            public void onFailure(Call<LatestDBResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<TopRatedMovie>> getTopRatedMovie() {

        final MutableLiveData<List<TopRatedMovie>> mutableLiveData = new MutableLiveData<>();

        Call<TopRatedDBResponse> call = service.getTopRatedMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<TopRatedDBResponse>() {
            @Override
            public void onResponse(Call<TopRatedDBResponse> call, Response<TopRatedDBResponse> response) {
                TopRatedDBResponse topRatedDBResponse = response.body();

                if (topRatedDBResponse != null && topRatedDBResponse.getTopRatedMovies() != null) {

                    List<TopRatedMovie> topRatedMovieList = topRatedDBResponse.getTopRatedMovies();
                    mutableLiveData.setValue(topRatedMovieList);

                }
            }

            @Override
            public void onFailure(Call<TopRatedDBResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<PopularTV>> getPopularTVSeries() {

        final MutableLiveData<List<PopularTV>> mutableLiveData = new MutableLiveData<>();

        Call<PopularTVResponse> call = service.getPopularTVSeries(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<PopularTVResponse>() {
            @Override
            public void onResponse(Call<PopularTVResponse> call, Response<PopularTVResponse> response) {
                PopularTVResponse popularTVResponse = response.body();

                if (popularTVResponse != null && popularTVResponse.getPopularTVs() != null) {

                    List<PopularTV> popularTVList = popularTVResponse.getPopularTVs();
                    mutableLiveData.setValue(popularTVList);

                }
            }

            @Override
            public void onFailure(Call<PopularTVResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<TopRatedTV>> getTopRatedTvSeries(){

        final MutableLiveData<List<TopRatedTV>> mutableLiveData = new MutableLiveData<>();

        Call<TopRatedTVResponse> call = service.getTopRatedTVSeries(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<TopRatedTVResponse>() {
            @Override
            public void onResponse(Call<TopRatedTVResponse> call, Response<TopRatedTVResponse> response) {
                TopRatedTVResponse topRatedTVResponse = response.body();

                if (topRatedTVResponse != null && topRatedTVResponse.getTopRatedTVs() != null){
                    List<TopRatedTV> topRatedTVList = topRatedTVResponse.getTopRatedTVs();
                    mutableLiveData.setValue(topRatedTVList);
                }
            }

            @Override
            public void onFailure(Call<TopRatedTVResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
