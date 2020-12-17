package com.example.movieapp.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movieapp.data.model.Result;

import java.util.List;


@Dao
public interface RoomDao {
    @Query("SELECT * FROM Result")
    LiveData<List<Result>> loadAllResults();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Result ResultEntry);


    @Delete
    void deleteMovie(Result ResultEntry);

    @Query("SELECT * FROM Result where title = :name")
    Result fetchCart(String name);
}
