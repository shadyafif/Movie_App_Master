package com.example.movieapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.model.ReviewsDetails.ReviewResult;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;
import com.example.movieapp.ui.acivities.MainActivity;
import com.example.movieapp.ui.fragments.ReviewerContentFragment;

import java.util.List;

import static com.example.movieapp.utilities.Helper.Replace;

public class ReviewersAdapter extends RecyclerView.Adapter<ReviewersAdapter.ViewHolder> {
    Context context;
    List<ReviewResult> ReviewersList;

    public ReviewersAdapter(Context context) {
        this.context = context;
    }

    public void setReviewersList(List<ReviewResult> reviewersList) {
        ReviewersList = reviewersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_reveiwer_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ReviewResult result = ReviewersList.get(position);
        holder.tv_ReviewerName.setText(result.getAuthor());
        holder.iv_Reviewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) context;
                ReviewerContentFragment reviewerContentFragment = new ReviewerContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Author", result.getAuthor());
                bundle.putString("Content", result.getContent());
                reviewerContentFragment.setArguments(bundle);
                Replace(reviewerContentFragment, R.id.FragmentLoad, mainActivity.getSupportFragmentManager().beginTransaction());
            }
        });
    }


    @Override
    public int getItemCount() {
        return ReviewersList != null ? ReviewersList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ReviewerName;
        ImageView iv_Reviewer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ReviewerName = itemView.findViewById(R.id.tv_ReviewerName);
            iv_Reviewer = itemView.findViewById(R.id.iv_Reviewer);
        }
    }
}
