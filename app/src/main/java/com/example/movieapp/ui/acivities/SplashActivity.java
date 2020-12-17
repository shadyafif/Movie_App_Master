package com.example.movieapp.ui.acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;


import com.example.movieapp.R;
import com.example.movieapp.utilities.MainBroadcast;
import com.example.movieapp.utilities.NetworkChangeReceiver;

import static com.example.movieapp.utilities.MainBroadcast.RegisterRecevir;

public class SplashActivity extends AppCompatActivity {
    private final int splashInterval = 3000;
    BroadcastReceiver br;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        br = new NetworkChangeReceiver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }, splashInterval);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RegisterRecevir(this, br, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister broad cast receiver to avoid memory leaks
        MainBroadcast.unregisterReceiver(this, br);
    }
}
