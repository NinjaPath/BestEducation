package com.ninjapath.besteducation.enums;

import java.io.Serializable;

public enum AccountType implements Serializable {


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

