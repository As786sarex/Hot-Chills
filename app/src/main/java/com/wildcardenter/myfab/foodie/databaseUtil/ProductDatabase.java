package com.wildcardenter.myfab.foodie.databaseUtil;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wildcardenter.myfab.foodie.models.Product;


/*
    Class On Package com.wildcardenter.myfab.foodie.databaseUtil
    
    Created by Asif Mondal on 03-10-2019 at 20:08
*/

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance = null;

    public static ProductDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ProductDatabase.class, "Product_Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
    public abstract ProductDao getProductDao();

}
