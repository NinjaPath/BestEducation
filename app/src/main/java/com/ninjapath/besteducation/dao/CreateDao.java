package com.ninjapath.besteducation.dao;

import android.view.View;

import com.ninjapath.besteducation.CourseData;

public interface CreateDao {
    void loadVideo(View view, CourseData courseData);
    void insertData(View view, CourseData courseData);
}
