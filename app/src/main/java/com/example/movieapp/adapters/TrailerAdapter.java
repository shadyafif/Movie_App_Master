package com.example.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.model.TrailersDetails.TrailerResult;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    Context context;
    List<TrailerResult> TrailersList;

    public TrailerAdapter(Context context) {
        this.context = context;
    }

    public void setTrailersList(List<TrailerResult> trailersList) {
        TrailersList = trailersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_trailer_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.ViewHolder holder, int position) {
        final TrailerResult result = TrailersList.get(position);
        holder.tv_TrailerName.setText(result.getName());
        holder.iv_Trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Key = result.getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + Key));
                intent.putExtra("VIDEO_ID", Key);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return TrailersList != null ? TrailersList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TrailerName;
        ImageButton iv_Trailer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TrailerName = itemView.findViewById(R.id.tv_TrailerName);
            iv_Trailer = itemView.findViewById(R.id.iv_Trailer);
        }
    }
}
