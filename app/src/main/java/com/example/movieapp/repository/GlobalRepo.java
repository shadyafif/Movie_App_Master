package com.example.movieapp.repository;

import com.example.movieapp.data.network.Api;
import com.example.movieapp.data.network.RetrofitClient;

public class GlobalRepo {
    private Api apiService =   RetrofitClient.getInstance().getApi();


    public Api getApiService() {
        return apiService;
    }
}
