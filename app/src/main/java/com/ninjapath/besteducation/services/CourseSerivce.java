package com.ninjapath.besteducation.services;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.ninjapath.besteducation.daoimpl.CreateDaoImpl;

public class CourseSerivce {
    private CreateDaoImpl createDao = new CreateDaoImpl();

    public void getCoursesRecyclerData(View view, RecyclerView coursesRecycler) {
        createDao.getCoursesRecyclerData(view,coursesRecycler);
    }
}
