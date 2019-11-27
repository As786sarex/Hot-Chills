package com.wildcardenter.myfab.foodie.models;

/*
                                #  #           #  #     
    Created by Asif Mondal on 23-11-2019 at 21:01
*/


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "fab_table")
public class Favorite {

    @PrimaryKey
    @NonNull
    private String productId;

    @Ignore
    public Favorite(@NotNull String productId) {
        this.productId = productId;
    }

    public Favorite() {
    }

    @NotNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NotNull String productId) {
        this.productId = productId;
    }
}
