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
import com.example.movieapp.adapters.ReviewersAdapter;
import com.example.movieapp.adapters.TrailerAdapter;
import com.example.movieapp.data.model.ReviewsDetails.ReviewResult;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;
import com.example.movieapp.databinding.FragmentReViewersBinding;
import com.example.movieapp.ui.viewmodels.ReviewersViewModel;
import com.example.movieapp.ui.viewmodels.TrailersViewModel;

import java.util.List;
import java.util.Objects;

public class ReViewersFragment extends Fragment {

    private FragmentReViewersBinding binding;
    private ReviewersAdapter reviewersAdapter;
    private ReviewersViewModel reviewersViewModel;

    public ReViewersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReViewersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        InitViews();
        return view;
    }

    private void InitViews() {
        Bundle bundle = this.getArguments();
        String title = "";
        int MovieId = 0;
        if (bundle != null) {
            title = bundle.getString("MovieTitle");
            MovieId = bundle.getInt("MovieId");
        }
        binding.toolbar.setTitle(title);
        binding.toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.RecReviewer.setLayoutManager(layoutManager);
        reviewersAdapter = new ReviewersAdapter(getContext());
        binding.RecReviewer.setAdapter(reviewersAdapter);
        reviewersViewModel = new ViewModelProvider(this).get(ReviewersViewModel.class);
        reviewersViewModel.getPopularList(MovieId);
        reviewersViewModel.GetDatumList().observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<ReviewResult>>() {
            @Override
            public void onChanged(List<ReviewResult> reviewResults) {
                reviewersAdapter.setReviewersList(reviewResults);
            }
        });
    }
}