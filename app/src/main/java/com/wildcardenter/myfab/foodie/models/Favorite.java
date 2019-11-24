package com.wildcardenter.myfab.foodie.models;

/*
                                #  #           #  #     
    Created by Asif Mondal on 23-11-2019 at 21:01
*/


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "fab_table", foreignKeys = @ForeignKey(entity =
        Product.class, parentColumns = "productId",
        childColumns = "productId"),indices = {@Index(value = "productId")})
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productId;

    @Ignore
    public Favorite(int id, String productId) {
        this.id = id;
        this.productId = productId;
    }

    public Favorite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
