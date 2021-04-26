package com.ninjapath.besteducation;

public enum AccountType {

    TEACHER("Teacher"),
    STUDENT("Student");
    private final String type;

    AccountType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
    
