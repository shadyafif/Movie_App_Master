package com.example.movieapp.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.movieapp.data.model.ReviewsDetails.ReviewResult;
import com.example.movieapp.repository.ReviewersRepo;

import java.util.List;

public class ReviewersViewModel extends AndroidViewModel {
    private ReviewersRepo reviewersRepo;
    public ReviewersViewModel(@NonNull Application application) {
        super(application);
        reviewersRepo= new ReviewersRepo();
    }

    public MutableLiveData<List<ReviewResult>> GetDatumList()
    {
        return reviewersRepo.getReviewsMovieList();
    }

    public void getPopularList(int MovieId)
    {
        reviewersRepo.GetReviewersMovieData(MovieId);
    }
}
