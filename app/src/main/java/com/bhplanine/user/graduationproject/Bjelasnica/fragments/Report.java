package com.bhplanine.user.graduationproject.Bjelasnica.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bhplanine.user.graduationproject.Bjelasnica.activities.PopUp;
import com.bhplanine.user.graduationproject.Bjelasnica.adapters.ImageReportAdapter;
import com.bhplanine.user.graduationproject.Bjelasnica.firebase.FirebaseHolder;
import com.bhplanine.user.graduationproject.Bjelasnica.models.Upload;
import com.bhplanine.user.graduationproject.Bjelasnica.utils.InternetConnection;
import com.bhplanine.user.graduationproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Report extends Fragment {

    private FirebaseHolder firebaseHolder = new FirebaseHolder(getActivity());
    private ArrayList<Upload> mUploads = new ArrayList<>();
    private InternetConnection connection = new InternetConnection();
    private String getMountain;
    private RecyclerView mRecyclerView;
    private ImageReportAdapter mAdapter;
    private ProgressBar mProgressCircle;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report3, container, false);
        onReportClick(v);
        buildRecyclerView(v);
        getMountain = String.valueOf(Objects.requireNonNull(getActivity()).getTitle());

        mProgressCircle = v.findViewById(R.id.progress_circle);
        if (connection.getInternetConnection()) {
            firebaseHolder.getDatabaseReferenceForReport().addValueEventListener(valueEventListener());
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.connect_internet), Toast.LENGTH_SHORT).show();
            loadUserReportPreferences();
            buildRecyclerAdapter();
            mProgressCircle.setVisibility(View.INVISIBLE);
        }
        return v;
    }

    private void buildRecyclerView(View v) {
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void buildRecyclerAdapter(){
        mAdapter = new ImageReportAdapter(getContext(), mUploads);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ValueEventListener valueEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Upload upload = postSnapshot.getValue(Upload.class);
                    if (postSnapshot.exists()){
                        mUploads.add(upload);
                    }
                }
                saveUserReportPreferences(mUploads);
                buildRecyclerAdapter();
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void saveUserReportPreferences(ArrayList<Upload> uploads) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getMountain + getResources().getString(R.string.sharedPreferencesReport), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(uploads);
            editor.putString(getMountain + getResources().getString(R.string.sharedPreferencesReport_list), json);
            editor.apply();
        }
    }

    private void loadUserReportPreferences() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getMountain + getResources().getString(R.string.sharedPreferencesReport), Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(getMountain + getResources().getString(R.string.sharedPreferencesReport_list), null);
            Type type = new TypeToken<ArrayList<Upload>>() {
            }.getType();
            mUploads = gson.fromJson(json, type);
        }
    }

    private void onReportClick(View v) {
        v.findViewById(R.id.fab).setOnClickListener(v1 -> {
            final Intent intent = new Intent(getActivity(), PopUp.class);
            startActivity(intent);
        });
    }

}

