package com.example.movieapp.adapters;

import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.model.Result;
import com.example.movieapp.ui.acivities.MainActivity;
import com.example.movieapp.ui.fragments.DetailsFragment;
import com.example.movieapp.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.movieapp.utilities.Helper.Replace;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Result> MoviesLists = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }


    public void setMoviesList(List<Result> moviesList) {
        this.MoviesLists = moviesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result ML = MoviesLists.get(position);
        final String Img_path = Constants.IMAGE_BASE_URL + ML.getPosterPath();

        Picasso.get().load(Img_path)
                .placeholder(R.drawable.progress_animation)
                .into(holder.img_MovieImage);


        holder.img_MovieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                DetailsFragment detailsFragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Movie", ML);
                detailsFragment.setArguments(bundle);
                Replace(detailsFragment, R.id.FragmentLoad,mainActivity.getSupportFragmentManager().beginTransaction())
                ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return MoviesLists != null ? MoviesLists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton img_MovieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_MovieImage = itemView.findViewById(R.id.img_MovieImage);
        }
    }
}
