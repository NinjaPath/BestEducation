package com.ninjapath.besteducation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.activities.CreateActivity;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton createButton = fragmentView.findViewById(R.id.create_course_button);
        createButton.setOnClickListener(view -> {
            Intent intentToCreateActivity = new Intent(getActivity(), CreateActivity.class);
            startActivity(intentToCreateActivity);
        });
        return fragmentView;
    }
}
