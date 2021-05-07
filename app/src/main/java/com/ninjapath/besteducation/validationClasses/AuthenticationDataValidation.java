package com.ninjapath.besteducation.validationClasses;

import com.ninjapath.besteducation.AuthenticationData;
import com.ninjapath.besteducation.LoginData;
import com.ninjapath.besteducation.enums.EntryErrorCode;
import com.ninjapath.besteducation.exceptions.EntryException;

import java.util.regex.Pattern;

public class AuthenticationDataValidation {

    public static void validateData(LoginData data) throws EntryException {
        validatePassword(data);
        validateEmail(data);
    }

    public static void validateEmail(LoginData loginData) throws EntryException {
        String textRegex = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(loginData.getEmail()).matches() || loginData.getEmail()
                .length() > 254 || loginData.getEmail()
                .length() < 3) {
            throw new EntryException(EntryErrorCode.WRONG_EMAIL);
        }
    }

    public static void validatePassword(LoginData loginData) throws EntryException {
        String textRegex = "^(?=.*?[A-Z]).{8,18}$";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(loginData.getPassword()).matches()){
            throw new EntryException(EntryErrorCode.WRONG_PASSWORD);
        }
    }


    public static void validateData(AuthenticationData authenticationData) throws EntryException {
        checkAllEntriesFilled(authenticationData);
        validateUsername(authenticationData);
        validateEmail(authenticationData);
        validatePassword(authenticationData);
        checkPasswordsDifference(authenticationData);
    }

    public static void checkAllEntriesFilled(AuthenticationData authenticationData) throws EntryException {
        if (authenticationData.getUsername().isEmpty() || authenticationData.getEmail().isEmpty()
                || authenticationData.getPassword().isEmpty() || authenticationData.
                getRepeatedPassword().isEmpty()) {
            throw new EntryException(EntryErrorCode.EMPTY_ENTRY);
        }
    }

    public static void validateUsername(AuthenticationData authenticationData) throws EntryException {
        String textRegex = "^[a-zA-Z0-9]{5,12}$";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(authenticationData.getUsername()).matches()) {
            throw new EntryException(EntryErrorCode.WRONG_NICKNAME);
        }
    }

    public static void validateEmail(AuthenticationData authenticationData) throws EntryException {
        String textRegex = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(authenticationData.getEmail()).matches() || authenticationData.getEmail()
                .length() > 254 || authenticationData.getEmail()
                .length() < 3) {
            throw new EntryException(EntryErrorCode.WRONG_EMAIL);
        }
    }

    public static void validatePassword(AuthenticationData authenticationData) throws EntryException {
        String textRegex = "^(?=.*?[A-Z]).{8,18}$";
        Pattern pat = Pattern.compile(textRegex);
        if (!pat.matcher(authenticationData.getPassword()).matches())
            throw new EntryException(EntryErrorCode.WRONG_PASSWORD);
    }

    public static void checkPasswordsDifference(AuthenticationData authenticationData) throws EntryException {
        if (!authenticationData.getPassword().equals(authenticationData.getRepeatedPassword())) {
            throw new EntryException(EntryErrorCode.DIFFERENT_PASSWORDS);
        }
    }
}
