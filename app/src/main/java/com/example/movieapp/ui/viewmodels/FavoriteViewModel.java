package com.example.movieapp.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapp.data.model.Result;
import com.example.movieapp.data.room.AppDataBase;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private LiveData<List<Result>> Movies;
    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        AppDataBase dataBase = AppDataBase.getInstance(this.getApplication());
        Movies= dataBase.ResultDao().loadAllResults();
    }

    public LiveData<List<Result>> getMovies() {
        return Movies;
    }
}
