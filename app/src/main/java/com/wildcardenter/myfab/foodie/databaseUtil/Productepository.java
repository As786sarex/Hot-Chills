package com.wildcardenter.myfab.foodie.databaseUtil;

/*
    Class On Package com.wildcardenter.myfab.foodie.databaseUtil
    
    Created by Asif Mondal on 03-10-2019 at 20:12
*/


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Productepository {

    private static final String TAG = "Productepository";


    private ProductDatabase database;
    private ProductDao productDao;
    private List<Product> products;


    public Productepository(Application application) {
        database = ProductDatabase.getDatabase(application);
        productDao = database.getProductDao();
        products = new ArrayList<>();
    }

    /**
     * <p>Saving the fetched products from the database to the Local storage.<p/>
     * Using an {@link AsyncTask} to store the data as synchronous task raise an {@link Exception }
     *
     * @param products List of products that to be stored on the local database
     */
    public void saveProducts(List<Product> products) {
        new StoreAsync(productDao, products).execute();
    }


    //Custom AsyncTask class
    private static class StoreAsync extends AsyncTask<Void, Void, Void> {
        private ProductDao dao;
        private List<Product> products;

        public StoreAsync(ProductDao dao, List<Product> products) {
            this.dao = dao;
            this.products = products;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            dao.saveProduct(products);
            return null;
        }
    }

    public void refreshProducts(String category) {
        FirebaseFirestore.getInstance().collection("Products").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    products.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        Product p = snapshot.toObject(Product.class);
                        products.add(p);
                    }
                    saveProducts(products);
                }).addOnFailureListener(e -> {
            Log.e(TAG,e.toString());
        });
    }


}
