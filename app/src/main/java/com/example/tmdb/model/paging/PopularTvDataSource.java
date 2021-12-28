package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.R;
import com.example.tmdb.model.PopularTV;
import com.example.tmdb.model.PopularTVResponse;
import com.example.tmdb.repository.Repository;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularTvDataSource extends PageKeyedDataSource<Long, PopularTV> {

    private Application application;
    private MovieDataService service;

    public PopularTvDataSource(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, PopularTV> callback) {

        service = RetrofitInstance.getInstance();

        Call<PopularTVResponse> call = service.getPopularTVSeriesWithPage(application.getApplicationContext().getString(R.string.api_key), 1);

        call.enqueue(new Callback<PopularTVResponse>() {
            @Override
            public void onResponse(Call<PopularTVResponse> call, Response<PopularTVResponse> response) {
                PopularTVResponse popularTVResponse = response.body();

                if (popularTVResponse != null && popularTVResponse.getPopularTVs()!=null){
                    List<PopularTV> popularTVList = popularTVResponse.getPopularTVs();
                    callback.onResult(popularTVList, null, (long) 2);
                }

            }

            @Override
            public void onFailure(Call<PopularTVResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, PopularTV> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, PopularTV> callback) {

        service = RetrofitInstance.getInstance();

        Call<PopularTVResponse> call = service.getPopularTVSeriesWithPage(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<PopularTVResponse>() {
            @Override
            public void onResponse(Call<PopularTVResponse> call, Response<PopularTVResponse> response) {
                PopularTVResponse popularTVResponse = response.body();

                if (popularTVResponse != null && popularTVResponse.getPopularTVs()!=null){
                    List<PopularTV> popularTVList = popularTVResponse.getPopularTVs();
                    callback.onResult(popularTVList, params.key+1);
                }

            }

            @Override
            public void onFailure(Call<PopularTVResponse> call, Throwable t) {

            }
        });

    }
}
