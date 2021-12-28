package com.example.tmdb.model.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.R;
import com.example.tmdb.model.TopRatedDBResponse;
import com.example.tmdb.model.TopRatedTV;
import com.example.tmdb.model.TopRatedTVResponse;
import com.example.tmdb.retrofit.MovieDataService;
import com.example.tmdb.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedTvDataSource extends PageKeyedDataSource<Long, TopRatedTV> {

    private Application application;
    private MovieDataService service;

    public TopRatedTvDataSource(Application application, MovieDataService service) {
        this.application = application;
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, TopRatedTV> callback) {

        service = RetrofitInstance.getInstance();

        Call<TopRatedTVResponse> call = service.getTopRatedTVSeriesWithPage(application.getApplicationContext().getString(R.string.api_key), 1);

        call.enqueue(new Callback<TopRatedTVResponse>() {
            @Override
            public void onResponse(Call<TopRatedTVResponse> call, Response<TopRatedTVResponse> response) {
                TopRatedTVResponse topRatedTVResponse = response.body();

                if (topRatedTVResponse != null && topRatedTVResponse.getTopRatedTVs() != null) {

                    List<TopRatedTV> topRatedTVList = topRatedTVResponse.getTopRatedTVs();
                    callback.onResult(topRatedTVList, null, (long) 2);

                }

            }

            @Override
            public void onFailure(Call<TopRatedTVResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, TopRatedTV> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, TopRatedTV> callback) {

        service = RetrofitInstance.getInstance();

        Call<TopRatedTVResponse> call = service.getTopRatedTVSeriesWithPage(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<TopRatedTVResponse>() {
            @Override
            public void onResponse(Call<TopRatedTVResponse> call, Response<TopRatedTVResponse> response) {
                TopRatedTVResponse topRatedTVResponse = response.body();

                if (topRatedTVResponse != null && topRatedTVResponse.getTopRatedTVs() != null) {

                    List<TopRatedTV> topRatedTVList = topRatedTVResponse.getTopRatedTVs();
                    callback.onResult(topRatedTVList, params.key + 1);

                }

            }

            @Override
            public void onFailure(Call<TopRatedTVResponse> call, Throwable t) {

            }
        });

    }
}
