package com.example.movieapp.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class MainBroadcast {
    private Context context;

    public MainBroadcast(Context context) {
        this.context = context;
    }

    public static void RegisterRecevir(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unregisterReceiver(Context context,BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }
}
