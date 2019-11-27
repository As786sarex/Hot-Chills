package com.wildcardenter.myfab.foodie.models;

/*
                                #  #           #  #     
    Created by Asif Mondal on 22-11-2019 at 02:05
*/


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "cart_items")
public class CartItems {

    @PrimaryKey
    @NonNull
    private String productId;
    private int itemCount;
    private int price;

    @Ignore
    public CartItems(@NotNull String productId, int itemCount, int price) {
        this.productId = productId;
        this.itemCount = itemCount;
        this.price = price;
    }

    public CartItems() {
    }


    @NotNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NotNull String productId) {
        this.productId = productId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
