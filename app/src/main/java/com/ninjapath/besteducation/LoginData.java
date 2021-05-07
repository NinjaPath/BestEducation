package com.ninjapath.besteducation;

import com.ninjapath.besteducation.enums.AccountType;

public class LoginData {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public LoginData( String email, String password) {
        this.email = email;
        this.password = password;
    }
}
