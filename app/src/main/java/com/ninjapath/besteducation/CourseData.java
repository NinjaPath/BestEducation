package com.ninjapath.besteducation;

import android.net.Uri;

public class CourseData {
    private String name;
    private String price;
    private Uri linkToVideo;
    private String subject;
    private String countOfPupils;
    private String courseDuration;

    public CourseData(String name, Uri linkToVideo, String price, String subject, String countOfPupils, String courseDuration) {
        this.name = name;
        this.linkToVideo = linkToVideo;
        this.price = price;
        this.subject = subject;
        this.countOfPupils = countOfPupils;
        this.courseDuration = courseDuration;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Uri getLinkToVideo() {
        return linkToVideo;
    }

    public String getSubject() {
        return subject;
    }

    public String getCountOfPupils() {
        return countOfPupils;
    }

    public String getCourseDuration() {
        return courseDuration;
    }
}
