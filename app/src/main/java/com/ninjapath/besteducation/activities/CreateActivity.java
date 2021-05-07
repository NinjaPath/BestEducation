package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ninjapath.besteducation.CourseData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.dto.CourseDataDTO;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.services.CreateService;
import com.ninjapath.besteducation.validationClasses.CourseDataValidation;

public class CreateActivity extends AppCompatActivity {
    private static final int REQUEST_TAKE_GALLERY_VIDEO = 13;
    private static final String TAG = "CreateActivity";
    private EditText courseNameEditText;
    private EditText coursePriceEditText;
    private Spinner subjectSpinner;
    private Spinner countSpinner;
    private Spinner lessonsTypeSpinner;
    private Uri linkToVideo;
    private CreateService createService = new CreateService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        //Setting spinners
        courseNameEditText = findViewById(R.id.course_name);
        coursePriceEditText = findViewById(R.id.course_price_edit_text);
        subjectSpinner = findViewById(R.id.subject_spinner);
        countSpinner = findViewById(R.id.count_spinner);
        lessonsTypeSpinner = findViewById(R.id.lessons_type);
        ImageButton addVideoButton = findViewById(R.id.add_video_button);
        Button createAccount = findViewById(R.id.sign_in_button);
        addVideoButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);
        });
        createAccount.setOnClickListener(view -> {
            //Creating courseData object
            CourseDataDTO courseDataDTO = new CourseDataDTO(courseNameEditText,
                    coursePriceEditText,
                    linkToVideo,
                    subjectSpinner,
                    countSpinner,
                    lessonsTypeSpinner);
            try {
                CourseDataValidation.validateData(courseDataDTO);
                CourseData courseData = new CourseData(courseDataDTO.getNameEditText().getText().toString(),
                        courseDataDTO.getLinkToVideo(), courseDataDTO.getPriceEditText().getText().toString(),
                        courseDataDTO.getSubjectSpinner().getSelectedItem().toString(),
                        courseDataDTO.getCountOfPupilsSpinner().getSelectedItem().toString(),
                        courseDataDTO.getCourseDurationSpinner().getSelectedItem().toString());
                createService.loadVideo(view, courseData);
                Log.d(TAG, "Video has been loaded successfully");
                createService.insertData(view, courseData);
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
                lessonsTypeSpinner.setAdapter(typeSpinnerAdapter);
                lessonsTypeSpinner.setSelection(0);
            } catch (EntryException exception) {
                SnackbarMessages.makeSnackbarError(view, exception.getErrorCode().getErrorString());
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
        lessonsTypeSpinner.setAdapter(typeSpinnerAdapter);
        lessonsTypeSpinner.setSelection(0);
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
