package com.blog.model;

import java.util.Date;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNo;
    private Date createdTime;

    public User(int id, String fullName, String email, String password, String phoneNo) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.createdTime = new Date();
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhoneNo() { return phoneNo; }
    public Date getCreatedTime() { return createdTime; }
}