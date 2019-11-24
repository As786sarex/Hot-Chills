package com.wildcardenter.myfab.foodie.databaseUtil;

/*
    Class On Package com.wildcardenter.myfab.foodie.databaseUtil
    
    Created by Asif Mondal on 03-10-2019 at 20:12
*/


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wildcardenter.myfab.foodie.helpers.Constants;
import com.wildcardenter.myfab.foodie.models.CartItems;
import com.wildcardenter.myfab.foodie.models.Favorite;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Productepository {

    private static final String TAG = "Productepository";


    private ProductDao productDao;
    private List<Product> productList;
    private CartItemDao cartItemDao;
    private FavoriteDao favoriteDao;
    private FirebaseAuth auth;

    public Productepository(Application application) {
        ProductDatabase database = ProductDatabase.getDatabase(application);
        productDao = database.getProductDao();
        cartItemDao = database.getCartItemDao();
        favoriteDao = database.getFavDao();
        auth = FirebaseAuth.getInstance();
        productList=new ArrayList<>();
    }

    /**
     * <p>Saving the fetched products from the database to the Local storage.<p/>
     * Using an {@link AsyncTask} to store the data as synchronous task raise an {@link Exception }
     *
     * @param products List of products that to be stored on the local database
     */
    private void saveProducts(List<Product> products) {
        new StoreAsync(productDao, products).execute();
    }

    public void saveCartItems(List<CartItems> items) {
        new StoreCartItem(cartItemDao, items).execute();
    }

    public void saveFavorites(List<Favorite> favoriteList) {
        new StoreFavoriteItem(favoriteDao, favoriteList).execute();
    }


    /*
    getting livedata of items
    */
    public LiveData<List<Product>> getAllCartItem() {
        return cartItemDao.getAllCartItem();
    }

    public LiveData<List<Favorite>> getAllFavorite() {
        return favoriteDao.getAllFavorite();
    }

    public LiveData<List<Product>> getAllProducts() {
        return productDao.getAllProducts();
    }

    //Custom AsyncTask class
    private static class StoreAsync extends AsyncTask<Void, Void, Void> {
        private ProductDao dao;
        private List<Product> products;

        StoreAsync(ProductDao dao, List<Product> products) {
            this.dao = dao;
            this.products = products;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            dao.saveProduct(products);
            return null;
        }
    }

    private static class StoreCartItem extends AsyncTask<Void, Void, Void> {
        private CartItemDao cartItemDao;
        private List<CartItems> list;

        StoreCartItem(CartItemDao cartItemDao, List<CartItems> list) {
            this.cartItemDao = cartItemDao;
            this.list = list;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cartItemDao.insertCartItem(list);
            return null;
        }
    }

    private static class StoreFavoriteItem extends AsyncTask<Void, Void, Void> {
        private FavoriteDao favoriteDao;
        private List<Favorite> list;

        StoreFavoriteItem(FavoriteDao favoriteDao, List<Favorite> list) {
            this.favoriteDao = favoriteDao;
            this.list = list;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDao.insertFavorite(list);
            return null;
        }
    }


    /*
    cart item recycler
     */

    public void updateCartItem(int id, int price, int count, String pid) {
        cartItemDao.updateCartItem(id, price, count);
    }

    public int sumOfPrice() {
        return cartItemDao.sumOfPrice();
    }

    public void deleteCartItem(int id, String productId) {
        //TODO: deleteCrtItemFromServer() method
    }


    public void refreshProducts() {
        FirebaseFirestore.getInstance().collection(Constants.PRODUCT_COLLECTION).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (productList != null)
                        productList.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        Product p = snapshot.toObject(Product.class);
                        productList.add(p);
                    }
                    saveProducts(productList);
                }).addOnFailureListener(e -> Log.e(TAG, e.toString()));
    }

    public void deleteCartItemFromServer(int id, String pid) {
        FirebaseDatabase.getInstance().getReference(Constants.CART_ITEM_REFER)
                .child(Objects.requireNonNull(auth.getUid()))
                .child(pid)
                .removeValue()
                .addOnSuccessListener(aVoid -> cartItemDao.deleteCartItem(id))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "deleteCrtItemFromServer: failed");
                });
    }

    public void updateCartItemFromServer(int id, int price, int count, String pid) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("itemCount", count);
        obj.put("price", price);
        FirebaseDatabase.getInstance().getReference(Constants.CART_ITEM_REFER)
                .child(Objects.requireNonNull(auth.getUid()))
                .child(pid)
                .updateChildren(obj)
                .addOnSuccessListener(aVoid -> {
                    cartItemDao.updateCartItem(id, price, count);
                });
    }

    public void deleteFavItem(int id, String pid) {
        FirebaseDatabase.getInstance().getReference(Constants.FAB_ITEM_REF)
                .child(Objects.requireNonNull(auth.getUid()))
                .child(pid)
                .removeValue()
                .addOnSuccessListener(i -> favoriteDao.deleteFromFab(id, pid))
                .addOnFailureListener(f -> Log.e(TAG, "deleteFavItem: failed"));
    }
}
