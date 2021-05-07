package com.ninjapath.besteducation;

public class AuthenticationData {
    private String accountType;
    private String username;
    private String email;

    public String getAccountType() {
        return accountType;
    }

    private String password;
    private String repeatedPassword;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public AuthenticationData(String accountType, String username, String email, String password, String repeatedPassword) {
        this.accountType = accountType;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

}
