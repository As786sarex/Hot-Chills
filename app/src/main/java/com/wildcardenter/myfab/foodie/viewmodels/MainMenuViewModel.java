package com.wildcardenter.myfab.foodie.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.wildcardenter.myfab.foodie.models.Product;

import java.util.ArrayList;
import java.util.List;


/*
    Class On Package com.wildcardenter.myfab.foodie.viewmodels
    
    Created by Asif Mondal on 04-10-2019 at 19:15
*/


public class MainMenuViewModel extends AndroidViewModel {
    String picUrl="https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg";
    String picUrl2="https://cdn.pixabay.com/photo/2016/11/29/13/02/cheese-1869708_960_720.jpg";
    private List<Product> list;

    public MainMenuViewModel(@NonNull Application application) {
        super(application);
        list=new ArrayList<>();
    }

    public List<Product> dummyProducts(){
        populateList();
        return list;
    }
    private void populateList() {
        list.clear();
        Product product=new Product("La Tomati Pizza",
                "ghjfgjfgfg",picUrl,"Italian",1,149);
        Product p2=new Product("Cheese Pizza","gjhgfggfg",
                picUrl2,"Continental",1,499);
        for (int i = 0; i <6 ; i++) {
            list.add(product);
            list.add(p2);
        }
    }

}
