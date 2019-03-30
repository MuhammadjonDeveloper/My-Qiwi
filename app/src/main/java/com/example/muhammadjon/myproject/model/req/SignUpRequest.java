package com.example.muhammadjon.myproject.model.req;

public class SignUpRequest {
    private String fistname;
    private String lastname;
    private String login;
    private String passwort;

    public SignUpRequest(String fistname,
                         String lastname,
                         String login,
                         String passwort) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.login = login;
        this.passwort = passwort;
    }
}
