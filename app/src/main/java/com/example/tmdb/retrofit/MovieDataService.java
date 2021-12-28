package com.example.tmdb.retrofit;

import com.example.tmdb.model.LatestDBResponse;
import com.example.tmdb.model.MovieDBResponse;
import com.example.tmdb.model.PopularTVResponse;
import com.example.tmdb.model.TopRatedDBResponse;
import com.example.tmdb.model.TopRatedTVResponse;
import com.example.tmdb.model.UpcomingDBRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesWithPage(@Query("api_key") String api_key, @Query("page") long page);

    @GET("movie/now_playing")
    Call<LatestDBResponse> getLatestMovies(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<LatestDBResponse> getLatestMoviesWithPage(@Query("api_key") String api_key, @Query("page") long page);

    @GET("movie/top_rated")
    Call<TopRatedDBResponse> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<TopRatedDBResponse> getTopRatedMoviesWithPage(@Query("api_key") String api_key, @Query("page") long page);

    @GET("movie/upcoming")
    Call<UpcomingDBRespone> getUpcomingMovies(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<UpcomingDBRespone> getUpcomingMoviesWithPage(@Query("api_key") String api_key, @Query("page") long page);

    @GET("tv/popular")
    Call<PopularTVResponse> getPopularTVSeries(@Query("api_key") String api_key);

    @GET("tv/popular")
    Call<PopularTVResponse> getPopularTVSeriesWithPage(@Query("api_key") String api_key, @Query("page") long page);

    @GET("tv/top_rated")
    Call<TopRatedTVResponse> getTopRatedTVSeries(@Query("api_key") String api_key);

    @GET("tv/top_rated")
    Call<TopRatedTVResponse> getTopRatedTVSeriesWithPage(@Query("api_key") String api_key, @Query("page") long page);


}
