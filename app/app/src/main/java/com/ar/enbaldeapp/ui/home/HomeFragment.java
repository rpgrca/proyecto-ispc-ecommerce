package com.ar.enbaldeapp.ui.home;

import static com.ar.enbaldeapp.ui.IntentConstants.CONTACT_MESSAGE_FOR_HOME;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.databinding.FragmentHomeBinding;
import com.ar.enbaldeapp.models.Configuration;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.Utilities;
import com.ar.enbaldeapp.ui.contact.ContactActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private final ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            o -> {
                if (o.getResultCode() == Activity.RESULT_OK) {
                    String message = o.getData().getStringExtra(CONTACT_MESSAGE_FOR_HOME);
                    Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
                }
            });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        IApiServices apiServices = new ApiServices();
        apiServices.getConfiguration(c -> {
                    List<Configuration> logo = c.stream().filter(p -> p.getName().equals("logoHeader")).collect(Collectors.toList());
                    if (! logo.isEmpty()) {
                        if (logo.get(0).getValue().startsWith("http")) {
                            SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getActivity());
                            sharedPreferencesManager.saveLogo(logo.get(0).getValue());
                            Utilities.insertLogoImageInto(getContext(), binding.homeMainLogo);
                        }
                    }
                },
                e -> {});

        binding.contactUs.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ContactActivity.class);
            intentLauncher.launch(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}