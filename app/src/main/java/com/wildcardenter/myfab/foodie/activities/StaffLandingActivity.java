package com.wildcardenter.myfab.foodie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.fragments.staff_core.AllProductsFragment;
import com.wildcardenter.myfab.foodie.fragments.staff_core.PendingOrderFragment;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;

public class StaffLandingActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    final PendingOrderFragment orderFragment = new PendingOrderFragment();
    final AllProductsFragment productsFragment = new AllProductsFragment();
    final FragmentManager manager = getSupportFragmentManager();
    Fragment active = orderFragment;
    private MainMenuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.staff_nav_view);
        manager.beginTransaction().add(R.id.staff_container, productsFragment, "2")
                .hide(productsFragment).commit();
        Toolbar toolbar = findViewById(R.id.staff_toolbar);
        setSupportActionBar(toolbar);
        manager.beginTransaction().add(R.id.staff_container, orderFragment, "1").commitNow();
        navigationView.setOnNavigationItemSelectedListener(this);
        viewModel = ViewModelProviders.of(this).get(MainMenuViewModel.class);
        viewModel.sysnProductWithServer();
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notifications_appbar) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
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
        }
        return false;
    }
}
