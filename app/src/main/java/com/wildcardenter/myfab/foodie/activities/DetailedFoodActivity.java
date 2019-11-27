package com.wildcardenter.myfab.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.models.CartItems;
import com.wildcardenter.myfab.foodie.models.Favorite;
import com.wildcardenter.myfab.foodie.models.Product;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;

import java.util.Arrays;
import java.util.Collections;

import es.dmoral.toasty.Toasty;

public class DetailedFoodActivity extends AppCompatActivity {
    private boolean isShown=false,isFav=false;
    private TextView name,category,quantity,price,details;
    private ImageView back,food_img,fav;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_food);
        Bundle b=getIntent().getExtras();
        if (b!=null){
            product= (Product) b.get("detailed_product");
        }

        // view inflation
        CardView detailed_view=findViewById(R.id.show_details_container);
        MainMenuViewModel viewModel= ViewModelProviders.of(this).get(MainMenuViewModel.class);
        name=findViewById(R.id.detailed_view_name);
        category=findViewById(R.id.detailed_view_category);
        quantity=findViewById(R.id.detailed_view_quantity);
        price=findViewById(R.id.detailed_view_price);
        details=findViewById(R.id.detailed_view_details);
        back=findViewById(R.id.detailed_view_back);
        food_img=findViewById(R.id.detailed_view_image);
        fav=findViewById(R.id.detailed_view_fav);
        if (product!=null){
            Picasso.with(this).load(product.getImageUrls()).into(food_img);
            name.setText(product.getProductName());
            category.setText(product.getCategory());
            price.setText(String.valueOf(product.getProductPrice()));
            String s=viewModel.isFabItemPresent(product.getProductId()).getValue();
            if (s!=null){
                isFav=true;
                fav.setImageResource(R.drawable.ic_favorite_black_24dp);
            }
        }
        back.setOnClickListener(view -> finish());
        fav.setOnClickListener(view->{
            if (!isFav){
                fav.setImageResource(R.drawable.ic_favorite_black_24dp);
                viewModel.addFabItem(Collections.singletonList(new Favorite(product.getProductId())));
                Toasty.success(this,"Favorite Added",Toasty.LENGTH_SHORT,true).show();
                isFav=true;
            }
            else {
                fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                Toasty.success(this,"Favorite Deleted",Toasty.LENGTH_SHORT,true).show();
                viewModel.deleteFabItem(product.getProductId());
                isFav=false;
            }
        });

        findViewById(R.id.detailed_view_more).setOnClickListener(i->{
            if (!isShown){
                detailed_view.setVisibility(View.VISIBLE);
                isShown=true;
            }
            else{
                detailed_view.setVisibility(View.GONE);
                isShown=false;
            }
        });
        findViewById(R.id.detailed_view_add_cart).setOnClickListener(i->{
            CartItems cartItems=new CartItems(product.getProductId(),1,product.getProductPrice());
            viewModel.insertCartIt(Collections.singletonList(cartItems));
            Toasty.success(this,"Successfully added",Toasty.LENGTH_SHORT,true).show();
        });
    }
}
