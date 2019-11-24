package com.wildcardenter.myfab.foodie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.fragments.staff_core.AllProductsFragment;
import com.wildcardenter.myfab.foodie.fragments.staff_core.PendingOrderFragment;
import com.wildcardenter.myfab.foodie.fragments.staff_core.PrintBillFragment;

public class StaffLandingActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    final PendingOrderFragment orderFragment=new PendingOrderFragment();
    final AllProductsFragment productsFragment=new AllProductsFragment();
    final PrintBillFragment billFragment=new PrintBillFragment();
    final FragmentManager manager=getSupportFragmentManager();
    Fragment active=orderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView=findViewById(R.id.staff_nav_view);
        manager.beginTransaction().add(R.id.staff_container,productsFragment,"2")
                .hide(productsFragment).commit();
        manager.beginTransaction().add(R.id.staff_container,billFragment,"3")
                .hide(billFragment).commit();
        manager.beginTransaction().add(R.id.staff_container,orderFragment,"1").commit();
        navigationView.setOnNavigationItemSelectedListener(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_pending_order:
                manager.beginTransaction().hide(active).show(orderFragment).commit();
                active = orderFragment;
                return true;
            case R.id.nav_all_products:
                manager.beginTransaction().hide(active).show(productsFragment).commit();
                active = productsFragment;
                return true;
            case R.id.nav_bill_print:
                manager.beginTransaction().hide(active).show(billFragment).commit();
                active = billFragment;
                return true;
        }
        return false;
    }
}
