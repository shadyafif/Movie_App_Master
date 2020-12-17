package com.example.movieapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.ReviewsDetails.ReviewResult;
import com.example.movieapp.data.model.ReviewsDetails.Reviews;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;
import com.example.movieapp.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewersRepo extends GlobalRepo{
    MutableLiveData<List<ReviewResult>> ReviewsMovieList = new MutableLiveData<>();
    List<ReviewResult> ReviewsList;

    public MutableLiveData<List<ReviewResult>> getReviewsMovieList() {
        return ReviewsMovieList;
    }

    public ReviewersRepo() {
    }

    public void GetReviewersMovieData(int MovieId) {
        getApiService().get_Movie_Reviews(MovieId, Constants.Api_key).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                ReviewsList = response.body().getResults();
                ReviewsMovieList.setValue(ReviewsList);
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
    }
}
