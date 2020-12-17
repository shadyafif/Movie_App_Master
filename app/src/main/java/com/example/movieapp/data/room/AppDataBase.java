package com.example.movieapp.data.room;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.movieapp.data.model.Result;


@Database(entities = {Result.class}, version = 1, exportSchema = false)
@TypeConverters(com.example.movieapp.data.room.DateConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    private static final String LOG_TAG = com.example.movieapp.data.room.AppDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "PopularMovie";
    private static  com.example.movieapp.data.room.AppDataBase sInstance;

    public static  com.example.movieapp.data.room.AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        com.example.movieapp.data.room.AppDataBase.class,  com.example.movieapp.data.room.AppDataBase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract RoomDao ResultDao();
}
