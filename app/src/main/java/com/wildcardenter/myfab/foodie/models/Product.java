package com.wildcardenter.myfab.foodie.models;

/*
    Class On Package com.wildcardenter.myfab.foodie.models
    
    Created by Asif Mondal on 21-09-2019 at 17:23
*/


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "products")
public class Product implements Serializable {
    @PrimaryKey
    @NonNull
    private String productId;
    private String productName;
    private String productDesc;
    private String imageUrls;
    @ColumnInfo(defaultValue = "all")
    private String category;
    private int isTrending;
    private int productPrice;

    @Ignore
    public Product(@NotNull String productName, String productDesc, String imageUrls, String category, int isTrending, int productPrice) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.imageUrls = imageUrls;
        this.category = category;
        this.isTrending = isTrending;
        this.productPrice = productPrice;
    }

    @Ignore
    public Product(@NonNull String productId, String productName, String productDesc,
                   String imageUrls, String category, int isTrending, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.imageUrls = imageUrls;
        this.category = category;
        this.isTrending = isTrending;
        this.productPrice = productPrice;
    }

    @NonNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NonNull String productId) {
        this.productId = productId;
    }

    public int getIsTrending() {
        return isTrending;
    }

    public void setIsTrending(int isTrending) {
        this.isTrending = isTrending;
    }

    public Product() {
    }

    @NotNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
