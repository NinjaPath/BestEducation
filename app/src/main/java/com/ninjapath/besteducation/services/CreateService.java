package com.ninjapath.besteducation.services;

import android.view.View;

import com.ninjapath.besteducation.CourseData;
import com.ninjapath.besteducation.daoimpl.CreateDaoImpl;

public class CreateService {
    private CreateDaoImpl createDao = new CreateDaoImpl();

    public void loadVideo(View view, CourseData courseData) {
        createDao.loadVideo(view, courseData);
    }

    public void insertData(View view, CourseData courseData) {
        createDao.insertData(view,courseData);
    }
}
