package com.wildcardenter.myfab.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.models.Product;

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
        }
        back.setOnClickListener(view -> finish());
        fav.setOnClickListener(view->{
            if (!isFav){
                fav.setImageResource(R.drawable.ic_favorite_black_24dp);
                isFav=true;
            }
            else {
                fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
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
        findViewById(R.id.detailed_view_add_cart).setOnClickListener(i->
                Toast.makeText(this, "To be developed", Toast.LENGTH_SHORT).show());
    }
}
