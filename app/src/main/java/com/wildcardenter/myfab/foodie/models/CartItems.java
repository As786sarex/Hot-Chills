package com.wildcardenter.myfab.foodie.models;

/*
                                #  #           #  #     
    Created by Asif Mondal on 22-11-2019 at 02:05
*/


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items", foreignKeys = @ForeignKey(entity = Product.class,
        parentColumns = "productId", childColumns = "productId"),indices = {@Index(value = "productId")})
public class CartItems {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productId;
    private int itemCount;
    private int price;

    @Ignore
    public CartItems(int id, String productId, int itemCount, int price) {
        this.productId = productId;
        this.itemCount = itemCount;
        this.price = price;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CartItems() {
    }

    @NonNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NonNull String productId) {
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
