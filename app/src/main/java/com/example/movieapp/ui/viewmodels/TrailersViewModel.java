package com.example.movieapp.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;
import com.example.movieapp.repository.TrailersRepo;

import java.util.List;

public class TrailersViewModel extends AndroidViewModel {
    private TrailersRepo trailersRepo;
    public TrailersViewModel(@NonNull Application application) {
        super(application);
        trailersRepo= new TrailersRepo();
    }

    public MutableLiveData<List<TrailerResult>> GetDatumList()
    {
        return trailersRepo.getTrailersMovieList();
    }

    public void getPopularList(int MovieId)
    {
        trailersRepo.GetTrailersMovieData(MovieId);
    }
}
