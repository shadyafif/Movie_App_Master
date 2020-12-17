package com.example.movieapp.ui.acivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapp.R;
import com.example.movieapp.ui.fragments.FavoritesFragment;
import com.example.movieapp.ui.fragments.MostPopularFragment;
import com.example.movieapp.ui.fragments.topRatedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.movieapp.utilities.Helper.Add;
import static com.example.movieapp.utilities.Helper.Replace;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        MostPopularFragment popularFragment = new MostPopularFragment();
        Add(popularFragment, R.id.FragmentLoad, getSupportFragmentManager().beginTransaction());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Favorite_menu:
                FavoritesFragment favoritesFragment = new FavoritesFragment();
                Replace(favoritesFragment, R.id.FragmentLoad, getSupportFragmentManager().beginTransaction());
                break;

            case R.id.Popular_menu:
                MostPopularFragment popularFragment = new MostPopularFragment();
                Replace(popularFragment, R.id.FragmentLoad, getSupportFragmentManager().beginTransaction());
                break;

            case R.id.TopRated_menu:
                topRatedFragment offersFragment = new topRatedFragment();
                Replace(offersFragment, R.id.FragmentLoad, getSupportFragmentManager().beginTransaction());
                break;

            default:
                break;
        }

        return true;
    }
}