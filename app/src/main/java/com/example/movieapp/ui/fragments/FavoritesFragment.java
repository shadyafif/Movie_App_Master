package com.example.movieapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.R;
import com.example.movieapp.adapters.FavoriteAdapter;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.data.room.AppDataBase;
import com.example.movieapp.data.room.AppExecutors;
import com.example.movieapp.databinding.FragmentFavoritesBinding;
import com.example.movieapp.ui.viewmodels.FavoriteViewModel;

import java.util.List;
import java.util.Objects;


public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private FavoriteAdapter favoriteAdapter;
    private FavoriteViewModel favoriteViewModel;
    private AppDataBase mDb;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        InitViews();
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        favoriteViewModel.getMovies().observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                favoriteAdapter.setFavMoviesList(results);
            }
        });
        return view;
    }

    public void InitViews() {
        binding.toolbar.setTitle(getContext().getResources().getString(R.string.favorite));
        binding.toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.RecFavorite.setLayoutManager(layoutManager);
        binding.RecFavorite.setHasFixedSize(true);
        favoriteAdapter = new FavoriteAdapter(getContext());
        binding.RecFavorite.setAdapter(favoriteAdapter);
        mDb = AppDataBase.getInstance(getContext());
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Result> datas = favoriteAdapter.getFavMoviesList();
                        mDb.ResultDao().deleteMovie(datas.get(position));

                    }
                });
            }
        }).attachToRecyclerView(binding.RecFavorite);


    }


}