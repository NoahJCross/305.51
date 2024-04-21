package com.example.a51youtube;

public class URL {

    private long id;
    private long userId;
    private String url;

    public URL(){

    }

    public URL(String url, long userId){
        this.userId = userId;
        this.url = url;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }
}
