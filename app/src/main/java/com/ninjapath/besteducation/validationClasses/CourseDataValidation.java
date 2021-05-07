package com.ninjapath.besteducation.validationClasses;

import com.ninjapath.besteducation.dto.CourseDataDTO;
import com.ninjapath.besteducation.enums.EntryErrorCode;
import com.ninjapath.besteducation.exceptions.EntryException;

import java.util.regex.Pattern;

public class CourseDataValidation {
    public static void validateData(CourseDataDTO courseData) throws EntryException {
        checkAllEntriesFilled(courseData);
        validateCourseName(courseData);
        checkVideoLinkEmpty(courseData);
        validatePrice(courseData);
    }

    private static void checkAllEntriesFilled(CourseDataDTO courseData) throws EntryException {
        if (courseData.getNameEditText().getText().toString().isEmpty() || courseData.
                getPriceEditText().toString().isEmpty()) {
            throw new EntryException(EntryErrorCode.EMPTY_ENTRY);
        }
    }

    private static void checkVideoLinkEmpty(CourseDataDTO courseData) throws EntryException {
        if (courseData.getLinkToVideo() == null) {
            throw new EntryException(EntryErrorCode.EMPTY_VIDEO_LINK);
        }
    }

    private static void validateCourseName(CourseDataDTO courseData) throws EntryException {
        String textRegex = "^.{3,32}$";
        Pattern pat = Pattern.compile(textRegex);
        String text = courseData.getNameEditText().getText().toString();
        if (!pat.matcher(text).matches()) {
            throw new EntryException(EntryErrorCode.WRONG_COURSE_NAME);
        }
    }

    private static void validatePrice(CourseDataDTO courseData) throws EntryException {
        String textRegex = "^[1-9]\\d{0,7}(?:\\.\\d{1,4})?$";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(courseData.getPriceEditText().getText().toString()).matches()) {
            throw new EntryException(EntryErrorCode.WRONG_PRICE);
        }
    }
}
