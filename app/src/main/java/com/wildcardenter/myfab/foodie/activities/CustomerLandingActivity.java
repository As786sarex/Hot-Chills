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
import com.wildcardenter.myfab.foodie.fragments.core_fragments.CartFragment;
import com.wildcardenter.myfab.foodie.fragments.core_fragments.FavoriteFragment;
import com.wildcardenter.myfab.foodie.fragments.core_fragments.MainMenuFragment;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;


public class CustomerLandingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    final MainMenuFragment mainMenuFragment = new MainMenuFragment();
    final FavoriteFragment favoriteFragment = new FavoriteFragment();
    final CartFragment cartFragment = new CartFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = mainMenuFragment;

    MainMenuViewModel mainMenuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing);
        Toolbar toolbar = findViewById(R.id.customer_toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        fm.beginTransaction().add(R.id.customer_container, favoriteFragment, "2")
                .hide(favoriteFragment).commit();
        fm.beginTransaction().add(R.id.customer_container, cartFragment, "3")
                .hide(cartFragment).commit();
        fm.beginTransaction().add(R.id.customer_container, mainMenuFragment, "1").commit();
        navigationView.setOnNavigationItemSelectedListener(this);
        mainMenuViewModel = ViewModelProviders.of(this).get(MainMenuViewModel.class);
        mainMenuViewModel.sysnProductWithServer();
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
            mainMenuViewModel.deleteAllCarts();
            mainMenuViewModel.deleteAllFabs();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fm.beginTransaction().hide(active).show(mainMenuFragment).commit();
                active = mainMenuFragment;
                return true;
            case R.id.navigation_favorite:
                fm.beginTransaction().hide(active).show(favoriteFragment).commit();
                active = favoriteFragment;
                return true;
            case R.id.navigation_cart:
                fm.beginTransaction().hide(active).show(cartFragment).commit();
                active = cartFragment;
                return true;
        }
        return false;
    }
}
