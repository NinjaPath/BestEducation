package com.ninjapath.besteducation;

public class AuthenticationData {
    private String username;
    private String email;
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

    public AuthenticationData(String username, String email, String password, String repeatedPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

}
