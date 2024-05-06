package com.ar.enbaldeapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ar.enbaldeapp.ui.Utilities;
import com.ar.enbaldeapp.ui.login.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ar.enbaldeapp.databinding.ActivityMainBinding;

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
                R.id.navigation_home, R.id.navigation_catalogue, R.id.navigation_cart, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //View view = this.findViewById(R.id.fragment_user_id);
/*
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.nav_view);
        if (Utilities.isLoggedIn(this))
        {
            //Utilities.replaceLoginWithProfile(view, bottomNavigationView, this.getSupportFragmentManager());
            Menu menu = bottomNavigationView.getMenu();
            MenuItem item = menu.findItem(R.id.navigation_user);
            item.setTitle("Profile");

            item = menu.findItem(R.id.navigation_cart);
            item.setEnabled(true);
            item.setVisible(true);

            item = menu.findItem(R.id.navigation_cart);
            item.setEnabled(true);
            item.setVisible(true);
        }
        else {
            //Utilities.replaceProfileWithLogin(view, this.getSupportFragmentManager());
            Menu menu = bottomNavigationView.getMenu();
            MenuItem item = menu.findItem(R.id.navigation_user);
            item.setTitle("Login");

            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}