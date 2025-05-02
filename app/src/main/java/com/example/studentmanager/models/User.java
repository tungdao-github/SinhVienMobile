package com.example.studentmanager.models;

//package com.example.studentmanager.;

public class User {
    public String username;
    public String password;
    public String role; // "admin" or "student"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
