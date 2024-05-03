package com.ar.enbaldeapp.ui.catalogue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.databinding.FragmentCatalogueBinding;
import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.ProductType;
import com.ar.enbaldeapp.services.CatalogueAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatalogueFragment extends Fragment {

    private FragmentCatalogueBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CatalogueViewModel catalogueViewModel =
                new ViewModelProvider(this).get(CatalogueViewModel.class);

        binding = FragmentCatalogueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewCatalogue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CatalogueAdapter adapter = new CatalogueAdapter(getCatalogue());
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListeners((position, product) -> {
            Toast.makeText(getActivity(), "Agregado", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    private List<Product> getCatalogue() {
        // TODO: Llamar al repositorio
        Map<Integer, ProductType> productTypes = Map.of(
                1, new ProductType(1, "Balde"),
                2, new ProductType(2, "Palito"),
                3, new ProductType(3, "Tacita"));

        return new ArrayList<>(List.of(
                new Product(1, "Helado de chocolate", "Un helado de chocolate Aguila", 3500, 10, "images/helado-chocolate.png", productTypes.get(1)),
                new Product(2, "Helado de frutilla", "Helado de frutilla con trozos de fruta natural", 5500, 6, "images/helado-frutilla.png", productTypes.get(2)),
                new Product(3, "Helado granizado", "Helado de dulce de leche con chips de chocolate", 1500, 20, "images/helado-granizado.png", productTypes.get(2))
        ));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}