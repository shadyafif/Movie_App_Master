package com.example.movieapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.MovieData;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;
import com.example.movieapp.data.model.TrailersDetails.Trailers;
import com.example.movieapp.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailersRepo extends GlobalRepo {
    MutableLiveData<List<TrailerResult>> TrailersMovieList = new MutableLiveData<>();
    List<TrailerResult> TrailersList;

    public MutableLiveData<List<TrailerResult>> getTrailersMovieList() {
        return TrailersMovieList;
    }

    public TrailersRepo() {
    }

    public void GetTrailersMovieData(int MovieId) {
        getApiService().get_Movie_Trailers(MovieId, Constants.Api_key).enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                TrailersList = response.body().getResults();
                TrailersMovieList.setValue(TrailersList);
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {

            }
        });
    }
}
