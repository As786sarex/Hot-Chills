package com.wildcardenter.myfab.foodie.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.fragments.core_fragments.CartFragment;
import com.wildcardenter.myfab.foodie.fragments.core_fragments.FavoriteFragment;
import com.wildcardenter.myfab.foodie.fragments.core_fragments.MainMenuFragment;
import com.wildcardenter.myfab.foodie.fragments.core_fragments.ProfileFragment;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;


public class CustomerLandingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    final MainMenuFragment mainMenuFragment = new MainMenuFragment();
    final FavoriteFragment favoriteFragment = new FavoriteFragment();
    final ProfileFragment profileFragment = new ProfileFragment();
    final CartFragment cartFragment = new CartFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = mainMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing);
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        fm.beginTransaction().add(R.id.customer_container, favoriteFragment, "2")
                .hide(favoriteFragment).commit();
        fm.beginTransaction().add(R.id.customer_container, cartFragment, "3")
                .hide(cartFragment).commit();
        fm.beginTransaction().add(R.id.customer_container, profileFragment, "4")
                .hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.customer_container, mainMenuFragment, "1").commit();
        navigationView.setOnNavigationItemSelectedListener(this);
        MainMenuViewModel mainMenuViewModel= ViewModelProviders.of(this).get(MainMenuViewModel.class);
        mainMenuViewModel.sysnProductWithServer();
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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
            case R.id.navigation_profile:
                fm.beginTransaction().hide(active).show(profileFragment).commit();
                active = profileFragment;
                return true;
        }
        return false;
    }
}
