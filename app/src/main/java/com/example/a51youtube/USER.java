package com.example.a51youtube;

import java.util.List;

public class USER {

    private static USER instance;
    private long id;
    private String fullName;
    private String username;
    private String password;

    // Private constructor
    private USER(){}

    // Singleton pattern: getInstance method
    public static USER getInstance() {
        if (instance == null) {
            instance = new USER();
        }
        return instance;
    }

    // Singleton pattern: getInstance method with parameters
    public static USER getInstance(String fullName, String username, String password) {
        if (instance == null) {
            instance = new USER();
            instance.fullName = fullName;
            instance.username = username;
            instance.password = password;
        }
        return instance;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
