package com.ar.enbaldeapp.ui;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ar.enbaldeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Utilities {
    public static void changeBottomMenuToLogin(View view) {
        changeBottomMenuTo(view, "Login");
    }

    public static void changeBottomMenuToProfile(View view) {
        changeBottomMenuTo(view, "Profile");
    }

    public static void changeBottomMenuToRegistration(View view) {
        changeBottomMenuTo(view, "Registration");
    }

    private static void changeBottomMenuTo(View view, String text) {
        BottomNavigationView bottomNavigationView = view.getRootView().findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem item = menu.findItem(R.id.navigation_user);
        item.setTitle(text);
    }
}
