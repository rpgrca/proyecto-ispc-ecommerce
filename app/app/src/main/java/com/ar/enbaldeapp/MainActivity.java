package com.ar.enbaldeapp;

import android.os.Bundle;
import android.view.View;

import com.ar.enbaldeapp.ui.Utilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ar.enbaldeapp.databinding.ActivityMainBinding;
import com.stripe.android.Stripe;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_catalogue, R.id.navigation_cart, R.id.navigation_history, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (Utilities.isLoggedIn(this)) {
            View view = this.findViewById(R.id.nav_host_fragment_activity_main);
            Utilities.changeBottomMenuToProfile(view);
            Utilities.showCartMenuItem(view);
            Utilities.showPreviousOrdersMenuItem(view);
        }
    }
}