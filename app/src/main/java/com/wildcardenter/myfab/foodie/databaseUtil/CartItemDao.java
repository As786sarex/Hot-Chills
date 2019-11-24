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

    @Query("delete from cart_items where id=:id")
    void deleteCartItem(int id);

    @Query("update cart_items set itemCount=:count,price=:price where id=:id")
    void updateCartItem(int id, int price, int count);

    @Query("select sum(price) from cart_items")
    int sumOfPrice();
}
