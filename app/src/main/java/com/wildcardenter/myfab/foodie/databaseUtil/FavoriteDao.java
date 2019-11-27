package com.wildcardenter.myfab.foodie.databaseUtil;

/*
                                #  #           #  #     
    Created by Asif Mondal on 23-11-2019 at 21:15
*/

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wildcardenter.myfab.foodie.models.Favorite;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(List<Favorite> favorite);

    @Query("select * from fab_table")
    LiveData<List<Favorite>> getAllFavorite();

    @Query("select * from products where productId in (select productId from fab_table)")
    LiveData<List<Product>> getAllFabProduvt();

    @Query("select productId from fab_table where productId=:pid")
    LiveData<String> isFabPresent(String pid);

    @Query("delete from fab_table where productId=:pid")
    void deleteFromFab(String pid);

    @Query("delete from fab_table")
    void deleteAllFab();

}
