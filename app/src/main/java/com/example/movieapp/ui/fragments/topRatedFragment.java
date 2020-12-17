package com.example.movieapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.adapters.MovieAdapter;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.databinding.FragmentTopRatedBinding;
import com.example.movieapp.ui.viewmodels.PopularViewModel;
import com.example.movieapp.ui.viewmodels.TopRatedViewModel;

import java.util.List;
import java.util.Objects;


public class topRatedFragment extends Fragment {

    private FragmentTopRatedBinding binding;
    private TopRatedViewModel topRatedViewModel;
    private MovieAdapter movieAdapter;

    public topRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTopRatedBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        InitView();
        topRatedViewModel=  new ViewModelProvider(this).get(TopRatedViewModel.class);
        topRatedViewModel.getTopRatedList();
        topRatedViewModel.GetDatumList().observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                binding.shimmerViewContainer.setVisibility(View.GONE);
                movieAdapter.setMoviesList(results);
            }
        });
        return view;
    }

    public void InitView() {
        binding.toolbar.setTitle(getContext().getResources().getString(R.string.top_rated));
        binding.toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        binding.shimmerViewContainer.startShimmer();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.RecTopRatedMovie.setLayoutManager(layoutManager);
        binding.RecTopRatedMovie.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(getContext());
        binding.RecTopRatedMovie.setAdapter(movieAdapter);
    }
}