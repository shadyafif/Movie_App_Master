package com.example.movieapp.data.network;

import com.example.movieapp.data.model.MovieData;
import com.example.movieapp.data.model.ReviewsDetails.Reviews;
import com.example.movieapp.data.model.TrailersDetails.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("movie/popular")
    Call<MovieData> get_popular(@Query("api_key") String key);

    @GET("movie/top_rated")
    Call<MovieData> get_top_rated(@Query("api_key") String key);

    @GET("movie/{id}/videos")
    Call<Trailers> get_Movie_Trailers(@Path("id") int id,
                                      @Query("api_key") String key);

    @GET("movie/{id}/reviews")
    Call<Reviews> get_Movie_Reviews(@Path("id") int id,
                                    @Query("api_key") String key);
}
