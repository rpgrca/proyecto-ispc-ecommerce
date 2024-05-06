package com.ar.enbaldeapp.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.ui.login.LoginFragment;
import com.ar.enbaldeapp.ui.profile.ProfileFragment;
import com.ar.enbaldeapp.ui.register.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Utilities {
    public static void replaceLoginWithProfile(View fragmentContainerView, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        //Menu menu = bottomNavigationView.getMenu();
        //MenuItem item = menu.findItem(R.id.navigation_user);

        //if (item != null && item.getTitle() != "Profile") {
            //hideLoginFragment(fragmentContainerView, bottomNavigationView, fragmentManager);
            //hideRegisterFragment(fragmentContainerView, bottomNavigationView, fragmentManager);
            showProfileFragment(fragmentContainerView, bottomNavigationView, fragmentManager);

//            item.setTitle("Profile");

//            item = menu.findItem(R.id.navigation_cart);
//            item.setEnabled(true);
//            item.setVisible(true);
//        }
    }

    private static void showProfileFragment(View activity, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        /*View containerView = activity.findViewById(R.id.user_fragment_container_view);
        View profile = activity.findViewById(R.id.fragment_profile_id);
        if (profile == null) {
            fragmentManager.beginTransaction()
                    .add(containerView.getId(), new ProfileFragment())
                    .addToBackStack("profile")
                    .commit();

            profile = activity.findViewById(R.id.fragment_profile_id);
        }*/
    }

    private static void hideLoginFragment(View activity, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        View login = activity.findViewById(R.id.fragment_login_id);
        if (login != null) {
            login.setVisibility(View.GONE);
            login.setEnabled(false);
        }
    }

    private static void hideRegisterFragment(View activity, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        View register = activity.findViewById(R.id.fragment_register_id);
        if (register != null) {
            register.setVisibility(View.GONE);
            register.setEnabled(false);
        }
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

    public static void replaceProfileWithLogin(View view, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        /*View profile = view.findViewById(R.id.fragment_profile_id);
        if (profile != null && profile.getVisibility() == View.VISIBLE) {
            hideRegisterFragment(view, bottomNavigationView, fragmentManager);
            hideProfileFragment(view, bottomNavigationView, fragmentManager);
            showLoginFragment(view, bottomNavigationView, fragmentManager);

            Menu menu = bottomNavigationView.getMenu();
            MenuItem item = menu.findItem(R.id.navigation_user);
            item.setTitle("Login");

            item = menu.findItem(R.id.navigation_cart);
            item.setVisible(false);
            item.setEnabled(false);
        }*/
    }

    private static void showLoginFragment(View view, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        /*View login = view.findViewById(R.id.fragment_login_id);
        if (login == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.user_fragment_container_view, new LoginFragment())
                    .addToBackStack("login")
                    .commit();

            //activity.findViewById(R.id.navigation_login);
        }*/
/*
        login.setVisibility(View.VISIBLE);
        login.setEnabled(true);*/
    }

    private static void hideProfileFragment(View userView, BottomNavigationView bottomNavigationView, FragmentManager fragmentManager) {
        /*View profile = userView.findViewById(R.id.fragment_profile_id);
        if (profile != null) {
            profile.setVisibility(View.GONE);
            profile.setEnabled(false);
        }*/
    }
}
