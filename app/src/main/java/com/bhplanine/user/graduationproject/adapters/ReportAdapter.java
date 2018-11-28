package com.bhplanine.user.graduationproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhplanine.user.graduationproject.models.Upload;
import com.bhplanine.user.graduationproject.activities.ZoomImageReport;
import com.bhplanine.user.graduationproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ImageViewHolder> {

    private final Context context;
    private final ArrayList<Upload> uploads;

    public ReportAdapter(final Context context, final ArrayList<Upload> uploads) {
        this.context = context;
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_report, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {
        final Upload uploadCurrent = uploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewUsername.setText(uploadCurrent.getmKey());
        holder.textTrailSituation.setText(uploadCurrent.getmTrail());
        holder.textSnow.setText(uploadCurrent.getmSnow());
        holder.date.setText(uploadCurrent.getDate());

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ZoomImageReport.class);
            intent.putExtra(context.getResources().getString(R.string.POSITION), uploadCurrent.getImageUrl());
            context.startActivity(intent);
        });

        Picasso.with(context)
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewName;
        final ImageView imageView;
        final TextView textViewUsername;
        final TextView textTrailSituation;
        final TextView textSnow;
        final TextView date;

        ImageViewHolder(final View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.Username);
            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
            textSnow = itemView.findViewById(R.id.textSnow);
            textTrailSituation = itemView.findViewById(R.id.textTrailSituation);
            date = itemView.findViewById(R.id.calendar);
        }
    }
}
