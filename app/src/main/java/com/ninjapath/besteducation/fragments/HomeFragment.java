package com.ninjapath.besteducation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.activities.CreateActivity;
import com.ninjapath.besteducation.services.CourseSerivce;

public class HomeFragment extends Fragment {
    private RecyclerView coursesRecycler;
    private CourseSerivce courseSerivce = new CourseSerivce();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        coursesRecycler = fragmentView.findViewById(R.id.coursers_recycler_view);
        courseSerivce.getCoursesRecyclerData(fragmentView,coursesRecycler);
        FloatingActionButton createButton = fragmentView.findViewById(R.id.create_course_button);
        createButton.setOnClickListener(view -> {
            Intent intentToCreateActivity = new Intent(getActivity(), CreateActivity.class);
            startActivity(intentToCreateActivity);
        });
        return fragmentView;
    }
}
