package com.ninjapath.besteducation.enums;

public enum EntryErrorCode {
    EMPTY_ENTRY("Заполните все поля!"),
    WRONG_NICKNAME("Логин должен содержать от 5 до 12 символов и являться валидным!"),
    WRONG_EMAIL("Введите действительную почту!"),
    WRONG_PASSWORD("Пароль должен содержать от 8 до 18 символов и являться валидным!"),
    DIFFERENT_PASSWORDS("Пароли не совпадают!"),
    WRONG_COURSE_NAME("Название курса должно содержать от 7 до 25 символов!"),
    WRONG_PRICE("Цена может находиться в диапозоне от 1 до 99.999 рублей!"),
    UNEXPECTED_ERROR("Неизвестная ошибка, попробуйте переподключиться к интернету."),
    EMPTY_VIDEO_LINK("Добавьте видео перед созданием курса!");

    private String errorString;

    EntryErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
