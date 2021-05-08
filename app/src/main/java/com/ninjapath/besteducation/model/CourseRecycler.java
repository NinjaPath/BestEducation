package com.ninjapath.besteducation.model;


import com.google.firebase.Timestamp;

public class CourseRecycler {
    private String courseName;
    private String subject;
    private String price;
    private String iconLink;
    private Timestamp timestamp;

    public CourseRecycler(String courseName, String subject, String price, String iconLink, Timestamp timestamp) {
        this.courseName = courseName;
        this.subject = subject;
        this.price = price;
        this.iconLink = iconLink;
        this.timestamp = timestamp;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSubject() {
        return subject;
    }

    public String getPrice() {
        return price;
    }

    public String getIconLink() {
        return iconLink;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
