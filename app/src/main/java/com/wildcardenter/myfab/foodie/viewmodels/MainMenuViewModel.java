package com.wildcardenter.myfab.foodie.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.wildcardenter.myfab.foodie.databaseUtil.Productepository;
import com.wildcardenter.myfab.foodie.helpers.Constants;
import com.wildcardenter.myfab.foodie.models.CartItems;
import com.wildcardenter.myfab.foodie.models.Favorite;
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
    private Productepository productRepository;
    public MainMenuViewModel(@NonNull Application application) {
        super(application);
        list=new ArrayList<>();
        productRepository=new Productepository(application);
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

    public void sysnProductWithServer(){
        productRepository.refreshProducts();
    }

    public LiveData<List<Product>> getAllProductsFromDb(){
        return productRepository.getAllProducts();
    }

    public void updateCart(String pid,int price,int count){
        productRepository.updateCartItem(pid,price,count);
    }

    public LiveData<List<Product>> getCartItems(){
        return productRepository.getAllCartItem();
    }
    public LiveData<Integer> getSum(){
        return productRepository.sumOfPrice();
    }

    public void insertCartIt(List<CartItems> cartItems){
        productRepository.saveCartItems(cartItems);
    }

    public void deleteCartsById(String pid){
        productRepository.deleteCartItem(pid);
    }
    public LiveData<List<CartItems>> getCartItemsRaw(){
        return productRepository.getAllCarts();
    }


    //add fab
    public void addFabItem(List<Favorite> list){
        productRepository.saveFavorites(list);
    }

    public void deleteFabItem(String pid){
        productRepository.deleteFavItem(pid);
    }

    public LiveData<List<Product>> getFavProduct(){
        return productRepository.getAllFabProductRep();
    }

    public LiveData<String> isFabItemPresent(String pid){
        return productRepository.isFabPresentRepo(pid);
    }

    public void deleteAllFabs(){
        productRepository.deleteAllFabItem();
    }
    public void deleteAllCarts(){
        productRepository.deleteAllCartItemRepo();
    }
}
