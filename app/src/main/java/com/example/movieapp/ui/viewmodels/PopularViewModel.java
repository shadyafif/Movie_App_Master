package com.example.movieapp.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.Result;
import com.example.movieapp.repository.PopularRepo;

import java.util.List;

public class PopularViewModel extends AndroidViewModel {
    private PopularRepo popularRepo;
    public PopularViewModel(@NonNull Application application) {
        super(application);
        popularRepo= new PopularRepo();
    }

    public MutableLiveData<List<Result>> GetDatumList()
    {
        return popularRepo.getPopularMovieList();
    }

    public void getPopularList()
    {
        popularRepo.GetPopularMovieData();
    }
}
