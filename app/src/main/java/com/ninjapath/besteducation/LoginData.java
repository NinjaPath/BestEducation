package com.ninjapath.besteducation;

public class LoginData {
    private String email;
    private String accountType;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getPassword() {
        return password;
    }

    public LoginData(String accountType, String email, String password) {
        this.accountType = accountType;
        this.email = email;
        this.password = password;
    }
}
