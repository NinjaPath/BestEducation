package com.ninjapath.besteducation.enums;

public enum EntryErrorCode {
    EMPTY_ENTRY("Заполните все поля!"),
    WRONG_NICKNAME("Логин должен содержать от 5 до 12 символов и являться валидным!"),
    WRONG_EMAIL("Введите действительную почту!"),
    WRONG_PASSWORD("Пароль должен содержать от 8 до 18 символов и являться валидным!"),
    DIFFERENT_PASSWORDS("Пароли не совпадают!");

    private String errorString;

    EntryErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
