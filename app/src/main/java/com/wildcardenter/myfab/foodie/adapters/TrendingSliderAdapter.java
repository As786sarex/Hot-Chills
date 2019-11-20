package com.wildcardenter.myfab.foodie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.List;


/*
    Class On Package com.wildcardenter.myfab.foodie.adapters
    
    Created by Asif Mondal on 12-10-2019 at 20:27
*/


public class TrendingSliderAdapter extends PagerAdapter {
    private Context context;
    private List<Product> list;

    public TrendingSliderAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trending_slider_item, container, false);//TODO:
        ImageView slider_img = view.findViewById(R.id.slider_image);
        TextView title = view.findViewById(R.id.slider_title);
        TextView category = view.findViewById(R.id.slider_category);
        TextView price=view.findViewById(R.id.slider_price);
        Product product=list.get(position);
        Picasso.with(context).load(product.getImageUrls()).into(slider_img);
        title.setText(product.getProductName());
        category.setText(product.getCategory());
        price.setText(String.valueOf(product.getProductPrice()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
