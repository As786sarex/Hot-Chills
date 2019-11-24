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

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(List<Favorite> favorite);

    @Query("select * from fab_table")
    LiveData<List<Favorite>> getAllFavorite();

    @Query("delete from fab_table where id=:id or productId=:pid")
    void deleteFromFab(int id,String pid);

}
