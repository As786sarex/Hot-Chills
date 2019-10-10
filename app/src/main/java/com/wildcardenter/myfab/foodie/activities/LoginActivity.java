package com.wildcardenter.myfab.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.fragments.login.CustomerLoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(getResources().getColor(R.color.loginStatusColor));
    }

    public void openCustomerLogin(View view) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.login_container, new CustomerLoginFragment()).commit();
    }
}
