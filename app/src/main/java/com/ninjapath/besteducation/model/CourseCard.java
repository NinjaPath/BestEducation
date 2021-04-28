package com.ninjapath.besteducation.model;

public class CourseCard {

    private String classLevel;
    private String linkToIcon;
//    private


    public CourseCard(String classLevel, String linkToIcon) {
        this.classLevel = classLevel;
        this.linkToIcon = linkToIcon;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public String getLinkToIcon() {
        return linkToIcon;
    }
}
