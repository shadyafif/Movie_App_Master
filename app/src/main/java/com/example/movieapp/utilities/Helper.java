package com.example.movieapp.utilities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Helper {
    public static void Replace(Fragment fragment, int id, FragmentTransaction fragmentTransaction) {
        FragmentTransaction transaction = fragmentTransaction;
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void Add(Fragment fragment, int id, FragmentTransaction fragmentTransaction) {
        FragmentTransaction transaction = fragmentTransaction;
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
