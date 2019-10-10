package com.wildcardenter.myfab.foodie.databaseUtil;

/*
    Class On Package com.wildcardenter.myfab.foodie.databaseUtil
    
    Created by Asif Mondal on 03-10-2019 at 19:44
*/

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wildcardenter.myfab.foodie.models.Product;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDao {
    @Insert(onConflict = REPLACE)
    void saveProduct(List<Product> products);

    @Query("Select * from products;")
    LiveData<List<Product>> getAllProducts();

    @Query("select * from products where category=:category")
    LiveData<List<Product>> getAllProductByCategory(String category);

    @Query("Select * from products where isTrending=1")
    LiveData<List<Product>> getAllTrendingProducts();
}
