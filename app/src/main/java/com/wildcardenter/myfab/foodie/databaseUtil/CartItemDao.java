package com.wildcardenter.myfab.foodie.databaseUtil;

/*
                                #  #           #  #     
    Created by Asif Mondal on 22-11-2019 at 02:10
*/


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wildcardenter.myfab.foodie.models.CartItems;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.List;

@Dao
public interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartItem(List<CartItems> items);

    @Query("select * from products where productId in (select productId from cart_items)")
    LiveData<List<Product>> getAllCartItem();

    @Query("select * from cart_items")
    LiveData<List<CartItems>> getCarts();

    @Query("delete from cart_items where productId=:pid")
    void deleteCartItem(String pid);

    @Query("update cart_items set itemCount=itemCount+:count,price=price+:price where productId=:pid")
    void updateCartItem(String pid, int price, int count);

    @Query("select sum(price) from cart_items")
    LiveData<Integer> sumOfPrice();

    @Query("delete from cart_items")
    void deleteAllCartItem();
}
