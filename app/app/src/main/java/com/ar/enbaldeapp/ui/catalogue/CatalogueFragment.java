package com.ar.enbaldeapp.ui.catalogue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ar.enbaldeapp.databinding.FragmentCatalogueBinding;

public class CatalogueFragment extends Fragment {

    private FragmentCatalogueBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CatalogueViewModel catalogueViewModel =
                new ViewModelProvider(this).get(CatalogueViewModel.class);

        binding = FragmentCatalogueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCatalogue;
        catalogueViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}