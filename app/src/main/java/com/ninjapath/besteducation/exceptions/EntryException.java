package com.ninjapath.besteducation.exceptions;

import com.ninjapath.besteducation.enums.EntryErrorCode;

public class EntryException extends Exception {
    private EntryErrorCode errorCode;

    public EntryException(EntryErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public EntryErrorCode getErrorCode() {
        return errorCode;
    }
}
