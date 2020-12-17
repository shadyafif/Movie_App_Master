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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Viewholder> {
    Context context;
    List<Result> FavMoviesList = new ArrayList<>();

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public List<Result> getFavMoviesList() {
        return FavMoviesList;
    }

    public void setFavMoviesList(List<Result> favMoviesList) {
        FavMoviesList = favMoviesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_favorite_layout, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final Result RL = FavMoviesList.get(position);

        final String Img_path = Constants.IMAGE_BASE_URL + RL.getPosterPath();

        Picasso.get().load(Img_path)
                .into(holder.img_MyFav);
        holder.img_MyFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                DetailsFragment detailsFragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Movie", RL);
                detailsFragment.setArguments(bundle);
                Replace(detailsFragment, R.id.FragmentLoad,mainActivity.getSupportFragmentManager().beginTransaction())
                ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return FavMoviesList != null ? FavMoviesList.size() : 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageButton img_MyFav;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_MyFav = itemView.findViewById(R.id.img_MyFav);
        }
    }
}
