package com.ar.enbaldeapp.ui.catalogue;

import static com.ar.enbaldeapp.ui.IntentConstants.CURRENT_USER_FOR_DETAIL;
import static com.ar.enbaldeapp.ui.IntentConstants.PRODUCT_FOR_DETAIL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.databinding.FragmentCatalogueBinding;
import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.adapters.CatalogueAdapter;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.details.ProductDetailActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CatalogueFragment extends Fragment {

    private FragmentCatalogueBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //CatalogueViewModel catalogueViewModel = new ViewModelProvider(this).get(CatalogueViewModel.class);

        binding = FragmentCatalogueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewCatalogue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CatalogueAdapter adapter = new CatalogueAdapter(getCatalogue());
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListeners((position, product) -> {
            Context context = getActivity();
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra(PRODUCT_FOR_DETAIL, product);

            User user = new SharedPreferencesManager(context).loadCurrentUser();
            intent.putExtra(CURRENT_USER_FOR_DETAIL, user);
            startActivity(intent);
        });

        return root;
    }

    private List<Product> getCatalogue() {
        AtomicReference<List<Product>> result = new AtomicReference<>();

        IApiServices apiServices = new ApiServices();
        apiServices.getCatalogue(
                result::set,
                e -> Snackbar.make(getView(), "Error obtaining catalogue: " + e.getMessage(), Snackbar.LENGTH_SHORT).show()
        );

        return result.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}