package com.ninjapath.besteducation;

import com.ninjapath.besteducation.enums.AccountType;

public class AuthenticationData {
    private AccountType accountType;
    private String username;
    private String email;

    public AccountType getAccountType() {
        return accountType;
    }

    public String getStringAccountType() {
        return accountType.toString();
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

    public AuthenticationData(AccountType accountType, String username, String email, String password, String repeatedPassword) {
        this.accountType = accountType;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

}
