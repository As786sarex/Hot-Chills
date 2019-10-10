package com.wildcardenter.myfab.foodie.models;

/*
    Class On Package com.wildcardenter.myfab.foodie.models
    
    Created by Asif Mondal on 21-09-2019 at 16:59
*/




abstract class User {
    private String name;
    private String email;
    private String uid;
    private int acCode;

    public User(String name, String email, String uid, int acCode) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.acCode = acCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAcCode() {
        return acCode;
    }

    public void setAcCode(int acCode) {
        this.acCode = acCode;
    }
}
