package com.example.movieapp.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.Result;
import com.example.movieapp.repository.TopRatedRepo;

import java.util.List;

public class TopRatedViewModel extends AndroidViewModel {
    private TopRatedRepo topRatedRepo;
    public TopRatedViewModel(@NonNull Application application) {
        super(application);
        topRatedRepo= new TopRatedRepo();
    }

    public MutableLiveData<List<Result>> GetDatumList()
    {
        return topRatedRepo.getTopRatedMovieList();
    }

    public void getTopRatedList()
    {
        topRatedRepo.GetTopRatedMovieData();
    }
}
