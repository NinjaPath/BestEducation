package com.ninjapath.besteducation.validationClasses;

import com.ninjapath.besteducation.CourseData;
import com.ninjapath.besteducation.enums.EntryErrorCode;
import com.ninjapath.besteducation.exceptions.EntryException;

import java.util.regex.Pattern;

public class CourseDataValidation {
    public static void validateData(CourseData courseData) throws EntryException {
        checkAllEntriesFilled(courseData);
        validateCourseName(courseData);
        validatePrice(courseData);
    }

    private static void checkAllEntriesFilled(CourseData courseData) throws EntryException {
        if (courseData.getName().isEmpty() || courseData.getPrice().isEmpty()) {
            throw new EntryException(EntryErrorCode.EMPTY_ENTRY);
        }
    }

    private static void validateCourseName(CourseData courseData) throws EntryException {
        String textRegex = "^[ А-Яа-яa-zA-Z0-9]{7,25}$";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(courseData.getName()).matches()) {
            throw new EntryException(EntryErrorCode.WRONG_COURSE_NAME);
        }
    }

    private static void validatePrice(CourseData courseData) throws EntryException {
        String textRegex = "^[1-9]\\d{0,7}(?:\\.\\d{1,4})?$";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(courseData.getPrice()).matches()) {
            throw new EntryException(EntryErrorCode.WRONG_PRICE);
        }
    }
}
