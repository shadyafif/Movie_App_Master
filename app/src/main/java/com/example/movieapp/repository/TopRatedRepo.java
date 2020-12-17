package com.example.movieapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.MovieData;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedRepo extends GlobalRepo{
    MutableLiveData<List<Result>> TopRatedMovieList = new MutableLiveData<>();
    List<Result> TopRatedList;
    public TopRatedRepo() {
    }

    public MutableLiveData<List<Result>> getTopRatedMovieList() {
        return TopRatedMovieList;
    }

    public void GetTopRatedMovieData() {
        getApiService().get_top_rated(Constants.Api_key).enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                MovieData movieData = response.body();
                if (movieData != null) {
                    TopRatedList = movieData.getResults();
                    TopRatedMovieList.setValue(TopRatedList);
                }
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {

            }
        });
    }
}
