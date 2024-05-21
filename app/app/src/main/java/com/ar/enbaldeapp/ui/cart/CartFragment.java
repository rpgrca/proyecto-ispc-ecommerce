package com.ar.enbaldeapp.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.databinding.FragmentCartBinding;
import com.ar.enbaldeapp.models.Cart;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.services.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getContext());
        User user = sharedPreferencesManager.loadCurrentUser();
        long cartId = sharedPreferencesManager.getCurrentCartId();
        String accessToken = sharedPreferencesManager.getAccessToken();

        IApiServices apiServices = new ApiServices();
        AtomicReference<CartViewModel> cartViewModel = new AtomicReference<>();
        apiServices.getCart(accessToken, cartId, c -> {
                    cartViewModel.set(new CartViewModel(c)); // new ViewModelProvider(this).get(CartViewModel.class);
                },
                e -> {
                    cartViewModel.set(new CartViewModel(new Cart(0, new ArrayList<>())));
                });

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CartAdapter adapter = new CartAdapter(getActivity(), cartViewModel.get());
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}