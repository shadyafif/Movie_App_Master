package com.example.movieapp.repository;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.MovieData;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularRepo extends GlobalRepo {
    MutableLiveData<List<Result>> PopularMovieList = new MutableLiveData<>();
    List<Result> PopularList;

    public MutableLiveData<List<Result>> getPopularMovieList() {
        return PopularMovieList;
    }

    public PopularRepo() {

    }

    public void GetPopularMovieData() {
        getApiService().get_popular(Constants.Api_key).enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                MovieData movieData = response.body();
                if (movieData != null) {
                    PopularList = movieData.getResults();
                    PopularMovieList.setValue(PopularList);
                }
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {

            }
        });
    }
}
