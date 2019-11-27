package com.wildcardenter.myfab.foodie.fragments.core_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.activities.PaymentActivity;
import com.wildcardenter.myfab.foodie.adapters.CartAdapter;
import com.wildcardenter.myfab.foodie.models.CartItems;
import com.wildcardenter.myfab.foodie.models.Order;
import com.wildcardenter.myfab.foodie.models.Product;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartAdapter.OnItemIteractListener {

    private List<Product> listl;
    private MainMenuViewModel viewModel;
    private TextView cart_price;
    private List<CartItems> CartList;
    private Integer sum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.cart_recycler);
        cart_price = view.findViewById(R.id.cart_product_price);
        viewModel = ViewModelProviders.of(this).get(MainMenuViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(manager);
        CartAdapter adapter = new CartAdapter(getContext());
        viewModel.getCartItems().observe(this, l -> {
            listl = l;
            adapter.setProductList(l);
            adapter.notifyDataSetChanged();
        });
        viewModel.getSum().observe(this, i -> {
            if (i == null) {
                sum = 0;
                cart_price.setText("0");
            } else {
                sum = i;
                cart_price.setText(String.valueOf(i));
            }
        });
        viewModel.getCartItemsRaw().observe(this, li -> {
            this.CartList = li;
            adapter.setCartList(li);
            adapter.notifyDataSetChanged();
        });
        adapter.setOnItemInteract(this);
        recyclerView.setAdapter(adapter);
        List<String> list=new ArrayList<>();
        view.findViewById(R.id.place_order_btn).setOnClickListener(i -> {
            if (sum != 0) {
                String id = FirebaseFirestore.getInstance().collection("Orders").document().getId();
                String uid;
                if (auth.getUid() == null) {
                    uid = id;
                } else {
                    uid = auth.getUid();
                }
                list.clear();
                String date= SimpleDateFormat.getDateTimeInstance().format(new Date());
                for (Product it:listl){
                    list.add(it.getProductName());
                }
                Order order = new Order(id, 7, uid,sum,System.currentTimeMillis(),date,true,list);
                Intent intent=new Intent(getContext(), PaymentActivity.class);
                intent.putExtra("order_details",order);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onInteract(int position, int what) {
        Product product = listl.get(position);
        if (what == PLUS) {
            viewModel.updateCart(product.getProductId(), product.getProductPrice(), 1);
        } else if (what == MINUS) {
            if (CartList.get(position).getItemCount() == 1) {
                viewModel.deleteCartsById(product.getProductId());
            } else {
                int x = product.getProductPrice();
                viewModel.updateCart(product.getProductId(), -x, -1);
            }
        }
    }
}
