package com.example.movieapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentReviewerContentBinding;


public class ReviewerContentFragment extends Fragment {

    private FragmentReviewerContentBinding binding;

    public ReviewerContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReviewerContentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        InitView();
        return view;
    }

    private void InitView() {
        Bundle bundle = this.getArguments();
        String author ="";
        String content = "";
        if (bundle != null) {
            author = bundle.getString("Author");
            content= bundle.getString("Content");
        }
        binding.toolbar.setTitle(author);
        binding.toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        binding.tvReviewContent.setText(content);
    }
}