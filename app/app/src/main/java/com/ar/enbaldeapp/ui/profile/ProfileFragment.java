package com.ar.enbaldeapp.ui.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.data.LoginDataSource;
import com.ar.enbaldeapp.data.Result;
import com.ar.enbaldeapp.databinding.FragmentProfileBinding;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiError;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.Utilities;
import com.google.android.material.snackbar.Snackbar;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button logoutButton = binding.profileLogoutButton;
        logoutButton.setOnClickListener(this::onLogout);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onLogout(View view) {
        Context context = getContext();
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
        String accessToken = sharedPreferencesManager.getAccessToken();

        IApiServices apiServices = new ApiServices();
        apiServices.logout(accessToken,
                (String message) -> doLogout(context, message),
                (ApiError e) -> doLogout(context, e.getMessage()));
    }

    private void doLogout(Context context, String message) {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
        sharedPreferencesManager.deleteCurrentUser();

        Navigation.findNavController(getView()).navigate(R.id.action_profileFragment_to_loginFragment);
        Utilities.changeToolbarTitleToLogin(getActivity());
        Utilities.changeBottomMenuToLogin(getView());
        Utilities.hideCartMenuItem(getView());

        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}