package com.ninjapath.besteducation.daoimpl;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ninjapath.besteducation.CourseData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.activities.MainActivity;
import com.ninjapath.besteducation.dao.CreateDao;
import com.ninjapath.besteducation.enums.EntryErrorCode;

import java.util.HashMap;
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
                Map<String, Object> courseInfo = new HashMap<>();
                courseInfo.put("name", courseData.getName());
                courseInfo.put("price", courseData.getPrice());
                courseInfo.put("videoLink", courseData.getLinkToVideo().getLastPathSegment());
                courseInfo.put("subject", courseData.getSubject());
                courseInfo.put("pupilsCount", courseData.getCountOfPupils());
                courseInfo.put("lessonType", courseData.getCourseDuration());
                courseInfo.put("owner", task.getResult().get("nickname"));
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
}
