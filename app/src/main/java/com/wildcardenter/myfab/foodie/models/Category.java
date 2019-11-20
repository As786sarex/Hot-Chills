package com.wildcardenter.myfab.foodie.models;

/*
    Class On Package com.wildcardenter.myfab.foodie.models
    
    Created by Asif Mondal on 12-10-2019 at 23:36
*/


public class Category {

    private String imageUrl;
    private String categoryName;
    private int categoryCode;

    public Category(String imageUrl, String categoryName, int categoryCode) {
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public Category() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
