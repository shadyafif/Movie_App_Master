package com.example.movieapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.adapters.TrailerAdapter;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;
import com.example.movieapp.databinding.FragmentTrailersBinding;
import com.example.movieapp.ui.viewmodels.TrailersViewModel;

import java.util.List;
import java.util.Objects;


public class TrailersFragment extends Fragment {

    private FragmentTrailersBinding binding;
    private TrailersViewModel trailersViewModel;
    private TrailerAdapter trailerAdapter;

    public TrailersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrailersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        InitViews();
        return view;
    }

    private void InitViews() {
        Bundle bundle = this.getArguments();
        String title = "";
        int MovieId = 0 ;
        if (bundle != null) {
            title = bundle.getString("MovieTitle");
            MovieId= bundle.getInt("MovieId");
        }
        binding.toolbar.setTitle(title);
        binding.toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.RecTrailer.setLayoutManager(layoutManager);
        trailerAdapter = new TrailerAdapter(getContext());
        binding.RecTrailer.setAdapter(trailerAdapter);
        trailersViewModel= new ViewModelProvider(this).get(TrailersViewModel.class);
        trailersViewModel.getPopularList(MovieId);
        trailersViewModel.GetDatumList().observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<TrailerResult>>() {
            @Override
            public void onChanged(List<TrailerResult> trailerResults) {
                trailerAdapter.setTrailersList(trailerResults);
            }
        });
    }
}