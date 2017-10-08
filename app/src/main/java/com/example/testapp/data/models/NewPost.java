package com.example.testapp.data.models;

public class NewPost {
    private int userId;
    private String title;
    private String body;

    public NewPost(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
