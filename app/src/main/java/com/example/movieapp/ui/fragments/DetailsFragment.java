package com.example.movieapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.data.room.AppDataBase;
import com.example.movieapp.data.room.AppExecutors;
import com.example.movieapp.databinding.FragmentDetailsBinding;
import com.example.movieapp.utilities.Constants;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import static com.example.movieapp.utilities.Helper.Replace;


public class DetailsFragment extends Fragment implements View.OnClickListener {

    private FragmentDetailsBinding binding;
    private Result result;
    private AppDataBase mDb;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        InitViews();
        return view;
    }

    private void InitViews() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            result = bundle.getParcelable("Movie");
        }
        String title = result.getOriginalTitle();
        binding.toolbar.setTitle(title);
        binding.toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        binding.tvDetailsMovieName.setText(result.getOriginalTitle());
        binding.tvDetailsMovieDate.setText(result.getReleaseDate());
        binding.tvDetailsMovieDescription.setText(result.getOverview());
        String Img_Big = Constants.IMAGE_BASE_URL + result.getPosterPath();
        String Img_path = Constants.IMAGE_BASE_URL + result.getBackdropPath();
        Picasso.get().load(Img_path)
//                .resize(0,800)
                .into(binding.ivDetailsBig);
        Picasso.get().load(Img_Big)
                .resize(0, 500)
                .into(binding.ivDetailsSmall);
        int number = (int) result.getVoteAverage();
        binding.RatingDetails.setRating(number);
        mDb = AppDataBase.getInstance(getContext());
        binding.btnDetailsReviews.setOnClickListener(this);
        binding.btnDetailsTrailers.setOnClickListener(this);
        binding.chkFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.chkFavorite.isChecked()) {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.ResultDao().insertMovie(result);
                        }
                    });

                    String Message = getResources().getString(R.string.checkChecked);
                    Snackbar.make(view, Message, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.ResultDao().deleteMovie(result);
                        }
                    });

                    String Message = getResources().getString(R.string.checkNotChecked);
                    Snackbar.make(view, Message, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }

        });

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                       Result resultName = mDb.ResultDao().fetchCart(result.getOriginalTitle());
                        if (resultName != null) {
                            binding.chkFavorite.setChecked(true);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_Details_Trailers) {
            Bundle bundleTrailers = new Bundle();
            TrailersFragment trailersFragment = new TrailersFragment();
            bundleTrailers.putInt("MovieId", result.getId());
            bundleTrailers.putString("MovieTitle", result.getOriginalTitle());
            trailersFragment.setArguments(bundleTrailers);
            Replace(trailersFragment, R.id.FragmentLoad, getActivity().getSupportFragmentManager().beginTransaction());
        } else if (id == R.id.btn_Details_Reviews) {
            Bundle bundleReviewers = new Bundle();
            ReViewersFragment reViewersFragment = new ReViewersFragment();
            bundleReviewers.putInt("MovieId", result.getId());
            bundleReviewers.putString("MovieTitle", result.getOriginalTitle());
            reViewersFragment.setArguments(bundleReviewers);
            Replace(reViewersFragment, R.id.FragmentLoad, getActivity().getSupportFragmentManager().beginTransaction());
        }
    }
}