package com.example.muhammadjon.myproject.model.req;

public class SignInRequest {
    private String login;
    private String passwort;

    public SignInRequest(String login,
                         String passwort) {
        this.login = login;
        this.passwort = passwort;
    }
}
