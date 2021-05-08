package com.ninjapath.besteducation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.adapters.CoursesRecycleViewAdapter;
import com.ninjapath.besteducation.adapters.TeacherCoursesAdapter;

public class ProfileFragment extends Fragment {

    RecyclerView courseRecycleView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        courseRecycleView = view.findViewById(R.id.courses_recycle_view);



        String[] links = new String[] {"https://firebasestorage.googleapis.com/v0/b/besteducation-993bf.appspot.com/o/course_icons%2Fatom%202.png?alt=media&token=dd9354a2-6b78-4ee5-ba9b-e878161b1efa"
        , "https://firebasestorage.googleapis.com/v0/b/besteducation-993bf.appspot.com/o/course_icons%2FVector.png?alt=media&token=d7e22af8-c7b4-49a5-bf91-ebede66a1406"};
        String[] signatures = new String[] {"Физика", "Математика"};

        TeacherCoursesAdapter adapter = new TeacherCoursesAdapter(links, signatures, getContext());
        courseRecycleView.setAdapter(adapter);
        LinearLayoutManager cardManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        courseRecycleView.setLayoutManager(cardManager);

        return view;

    }
}
