package com.ninjapath.besteducation.dto;

import android.net.Uri;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

public class CourseDataDTO {
    private EditText nameEditText;
    private EditText priceEditText;
    private Uri linkToVideo;
    private Spinner subjectSpinner;
    private Spinner countOfPupilsSpinner;
    private Spinner courseDurationSpinner;

    public CourseDataDTO(EditText nameEditText, EditText priceEditText, Uri linkToVideo, Spinner subjectSpinner, Spinner countOfPupilsSpinner, Spinner courseDurationSpinner) {
        this.nameEditText = nameEditText;
        this.priceEditText = priceEditText;
        this.linkToVideo = linkToVideo;
        this.subjectSpinner = subjectSpinner;
        this.countOfPupilsSpinner = countOfPupilsSpinner;
        this.courseDurationSpinner = courseDurationSpinner;
    }

    public EditText getNameEditText() {
        return nameEditText;
    }

    public EditText getPriceEditText() {
        return priceEditText;
    }

    public Uri getLinkToVideo() {
        return linkToVideo;
    }

    public Spinner getSubjectSpinner() {
        return subjectSpinner;
    }

    public Spinner getCountOfPupilsSpinner() {
        return countOfPupilsSpinner;
    }

    public Spinner getCourseDurationSpinner() {
        return courseDurationSpinner;
    }

    public void setNameEditText(EditText nameEditText) {
        this.nameEditText = nameEditText;
    }

    public void setPriceEditText(EditText priceEditText) {
        this.priceEditText = priceEditText;
    }

    public void setLinkToVideo(Uri linkToVideo) {
        this.linkToVideo = linkToVideo;
    }

    public void setSubjectSpinner(Spinner subjectSpinner) {
        this.subjectSpinner = subjectSpinner;
    }

    public void setCountOfPupilsSpinner(Spinner countOfPupilsSpinner) {
        this.countOfPupilsSpinner = countOfPupilsSpinner;
    }

    public void setCourseDurationSpinner(Spinner courseDurationSpinner) {
        this.courseDurationSpinner = courseDurationSpinner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDataDTO that = (CourseDataDTO) o;
        return Objects.equals(nameEditText, that.nameEditText) &&
                Objects.equals(priceEditText, that.priceEditText) &&
                Objects.equals(linkToVideo, that.linkToVideo) &&
                Objects.equals(subjectSpinner, that.subjectSpinner) &&
                Objects.equals(countOfPupilsSpinner, that.countOfPupilsSpinner) &&
                Objects.equals(courseDurationSpinner, that.courseDurationSpinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameEditText, priceEditText, linkToVideo, subjectSpinner, countOfPupilsSpinner, courseDurationSpinner);
    }
}
