package com.blog.model;

import java.util.Date;

public class Blog {
    private int id;
    private String title;
    private String contents;
    private Date createdTime;
    private int userId;
    private int categoryId;

    public Blog(int id, String title, String contents, int userId, int categoryId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.categoryId = categoryId;
        this.createdTime = new Date();
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public Date getCreatedTime() { return createdTime; }
    public int getUserId() { return userId; }
    public int getCategoryId() { return categoryId; }
}