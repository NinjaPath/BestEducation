package com.ninjapath.besteducation.daoimpl;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ninjapath.besteducation.CourseData;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.activities.MainActivity;
import com.ninjapath.besteducation.adapters.CoursesRecycleViewAdapter;
import com.ninjapath.besteducation.dao.CreateDao;
import com.ninjapath.besteducation.enums.EntryErrorCode;
import com.ninjapath.besteducation.model.CourseRecycler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateDaoImpl implements CreateDao {
    private FirebaseStorage storage;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    @Override
    public void loadVideo(View view, CourseData courseData) {
        storage = FirebaseStorage.getInstance();
        StorageReference videosRef = storage.getReference().child("videos/" + courseData.
                getLinkToVideo().getLastPathSegment());
        UploadTask uploadTask = videosRef.putFile(courseData.getLinkToVideo());
        uploadTask.addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                SnackbarMessages.makeSnackbarError(view, EntryErrorCode.UNEXPECTED_ERROR.
                        getErrorString());
            }
        });
    }

    @Override
    public void insertData(View view, CourseData courseData) {
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        fstore.collection("users").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Timestamp timestamp = new Timestamp(new Date());
                Map<String, Object> courseInfo = new HashMap<>();
                courseInfo.put("name", courseData.getName());
                courseInfo.put("price", courseData.getPrice());
                courseInfo.put("videoLink", courseData.getLinkToVideo().getLastPathSegment());
                courseInfo.put("subject", courseData.getSubject());
                courseInfo.put("pupilsCount", courseData.getCountOfPupils());
                courseInfo.put("lessonType", courseData.getCourseDuration());
                courseInfo.put("owner", task.getResult().get("nickname"));
                courseInfo.put("creationDate", timestamp);
                fstore.collection("courses").document().set(courseInfo).addOnCompleteListener(task1 -> {
                    if (task.isSuccessful()) {
                        SnackbarMessages.makeSnackbarNotify(view, "Курс создан успешно!");  //Resources.getSystem().getString(R.string.course_created_successfully));
                        Intent intentToMainActivity = new Intent(view.getContext(),
                                MainActivity.class);
                        view.getContext().startActivity(intentToMainActivity);
                    } else {
                        SnackbarMessages.makeSnackbarError(view, EntryErrorCode.UNEXPECTED_ERROR.
                                getErrorString());
                    }
                });
            } else {
                SnackbarMessages.makeSnackbarError(view, EntryErrorCode.UNEXPECTED_ERROR.
                        getErrorString());
            }
        });
    }


    //TODO Rewrite this part of code
    @Override
    public void getCoursesRecyclerData(View view, RecyclerView coursesRecycler) {
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        List<CourseRecycler> coursesList = new ArrayList<>();
        fstore.collection("users").
                document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String nickname = task.getResult().getString("nickname");
                fstore.collection("courses").whereEqualTo("owner", nickname).get()
                        .addOnCompleteListener(task1 -> {
                            if (task.isSuccessful()) {
                                getCourseInfo(coursesList, task1, view, coursesRecycler);
                            } else {
                                SnackbarMessages.makeSnackbarError(view, EntryErrorCode.
                                        UNEXPECTED_ERROR.getErrorString());
                            }
                        });
            } else {
                SnackbarMessages.makeSnackbarError(view, EntryErrorCode.UNEXPECTED_ERROR.
                        getErrorString());
            }
        });
    }

    //TODO Rewrite this part of code
    private void getCourseInfo(List<CourseRecycler> coursesList, Task<QuerySnapshot> task1, View view,
                               RecyclerView coursesRecycler) {
        for (QueryDocumentSnapshot documentSnapshot : task1.getResult()) {
            String name = documentSnapshot.getString("name");
            String subject = documentSnapshot.getString("subject");
            String price = documentSnapshot.getString("price");
            Timestamp timestamp = documentSnapshot.getTimestamp("creationDate");
            fstore.collection("icons").whereEqualTo("subject", subject).get().
                    addOnCompleteListener(task2 -> {
                        if (task2.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot1 : task2.getResult()) {
                                String iconLink = documentSnapshot1.getString("iconLink");
                                coursesList.add(new CourseRecycler(name, subject, price, iconLink, timestamp));
                            }
                            sortCourseList(coursesList);
                            CoursesRecycleViewAdapter coursesRecycleViewAdapter = new CoursesRecycleViewAdapter(coursesList);
                            coursesRecycler.setAdapter(coursesRecycleViewAdapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),
                                    LinearLayoutManager.VERTICAL, false);
                            coursesRecycler.setLayoutManager(linearLayoutManager);
                        } else {
                            SnackbarMessages.makeSnackbarError(view,
                                    EntryErrorCode.UNEXPECTED_ERROR.getErrorString());
                        }
                    });
        }
    }

    private static void sortCourseList(List<CourseRecycler> courseRecyclerList) {
        Collections.sort(courseRecyclerList, (t0, t1) -> t0.getTimestamp().compareTo(t1.getTimestamp()));
    }

}
