package com.ar.enbaldeapp.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Utilities {
    public static void replaceLoginWithProfile(Activity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();

        MenuItem item = menu.findItem(R.id.navigation_login);
        item.setVisible(false);
        item.setEnabled(false);

        item = menu.findItem(R.id.navigation_cart);
        item.setVisible(true);
        item.setEnabled(true);

        item = menu.findItem(R.id.navigation_profile);
        item.setVisible(true);
        item.setEnabled(true);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    public static void saveUserToPreferences(Activity activity, User model) {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(activity);
        sharedPreferencesManager.saveCurrentUser(model);
    }

    public static boolean isLoggedIn(Activity activity) {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(activity);
        User user = sharedPreferencesManager.loadCurrentUser();
        return user != null;
    }

    public static void replaceProfileWithLogin(Activity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();

        MenuItem item = menu.findItem(R.id.navigation_cart);
        item.setVisible(false);
        item.setEnabled(false);

        item = menu.findItem(R.id.navigation_profile);
        item.setVisible(false);
        item.setEnabled(false);

        item = menu.findItem(R.id.navigation_login);
        item.setVisible(true);
        item.setEnabled(true);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
}
