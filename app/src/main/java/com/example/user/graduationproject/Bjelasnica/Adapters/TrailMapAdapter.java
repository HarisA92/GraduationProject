package com.example.user.graduationproject.Bjelasnica.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.graduationproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrailMapAdapter extends RecyclerView.Adapter<TrailMapAdapter.ViewHolder> {
    private final ArrayList<String> list;
    private final Context context;

    public TrailMapAdapter(final ArrayList<String> list, final Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailmap, parent, false);
        return new TrailMapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position)).centerCrop().resize(1280, 720).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}