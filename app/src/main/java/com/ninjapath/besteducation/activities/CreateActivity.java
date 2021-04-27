package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ninjapath.besteducation.CourseData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.validationClasses.CourseDataValidation;

public class CreateActivity extends AppCompatActivity {
    private static final int REQUEST_TAKE_GALLERY_VIDEO = 13;
    private static final String TAG = "CreateActivity";
    private EditText courseNameEditText;
    private EditText coursePriceEditText;
    private Spinner subjectSpinner;
    private Spinner countSpinner;
    private Spinner typeSpinner;
    private ImageButton addVideoButton;
    private Uri linkToVideo;
    private Button createAccount;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        //Setting spinners
        courseNameEditText = findViewById(R.id.course_name);
        coursePriceEditText = findViewById(R.id.course_price_edit_text);
        subjectSpinner = findViewById(R.id.subject_spinner);
        countSpinner = findViewById(R.id.count_spinner);
        typeSpinner = findViewById(R.id.type_spinner);
        addVideoButton = findViewById(R.id.add_video_button);
        createAccount = findViewById(R.id.sign_in_button);
        addVideoButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);
        });
        createAccount.setOnClickListener(view -> {
            CourseData courseData = new CourseData(courseNameEditText.getText().toString(),
                    linkToVideo, coursePriceEditText.getText().toString(),
                    subjectSpinner.getSelectedItem().
                            toString(), countSpinner.getSelectedItem().toString(),
                    typeSpinner.getSelectedItem().toString());
            try {
                CourseDataValidation.validateData(courseData);
                StorageReference storageRef = storage.getReference();
                StorageReference videosRef = storageRef.child("videos/" + courseData.
                        getLinkToVideo().getLastPathSegment());
                UploadTask uploadTask = videosRef.putFile(courseData.getLinkToVideo());
                uploadTask.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                    } else {
                        SnackbarMessages.makeSnackbarError(view,
                                getString(R.string.unexpected_error));
                    }
                });
            } catch (EntryException e) {
                SnackbarMessages.makeSnackbarError(view, e.getErrorCode().getErrorString());
            }
        });
        ArrayAdapter<CharSequence> subjectSpinnerAdapter = ArrayAdapter.
                createFromResource(this, R.array.school_subject,
                        R.layout.spinner_item);
        subjectSpinnerAdapter.setDropDownViewResource(R.layout.dropdown_spinner_item);
        subjectSpinner.setAdapter(subjectSpinnerAdapter);
        subjectSpinner.setSelection(0);
        ArrayAdapter<CharSequence> countSpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.group_name, R.layout.spinner_item);
        countSpinnerAdapter.setDropDownViewResource(R.layout.dropdown_spinner_item);
        countSpinner.setAdapter(countSpinnerAdapter);
        countSpinner.setSelection(0);
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.lessons_type, R.layout.spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.dropdown_spinner_item);
        typeSpinner.setAdapter(typeSpinnerAdapter);
        typeSpinner.setSelection(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                linkToVideo = data.getData();
            }
        }
    }

}
