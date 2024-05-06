package com.ar.enbaldeapp.ui.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.databinding.FragmentProfileBinding;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.Utilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button logoutButton = binding.logoutButton;
        logoutButton.setOnClickListener(this::onLogout);

/*        final TextView textView = binding.textProfile;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onLogout(View view) {
        Context context = getContext();
        IApiServices apiServices = new ApiServices();
        apiServices.logout(() ->
                {
                    SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
                    sharedPreferencesManager.deleteCurrentUser();

                    Navigation.findNavController(getView()).navigate(R.id.action_profileFragment_to_loginFragment);
                    Utilities.changeBottomMenuToLogin(getView());
                },
                () -> Toast.makeText(context, "Error trying to log out, please try again.", Toast.LENGTH_SHORT).show());
    }
}