package com.ar.enbaldeapp.ui.cart;

import static com.ar.enbaldeapp.ui.IntentConstants.ACCESS_TOKEN_FOR_PAYMENT;
import static com.ar.enbaldeapp.ui.IntentConstants.CURRENT_CART_FOR_PAYMENT;
import static com.ar.enbaldeapp.ui.IntentConstants.CURRENT_USER_FOR_PAYMENT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.ar.enbaldeapp.ui.payment.PaymentActivity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private final ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            o -> {
                if (o.getResultCode() == Activity.RESULT_OK) {


                }
            });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getContext());
        long cartId = sharedPreferencesManager.getCurrentCartId();
        User user = sharedPreferencesManager.loadCurrentUser();
        String accessToken = sharedPreferencesManager.getAccessToken();
        AtomicReference<Cart> cart = new AtomicReference<>();

        IApiServices apiServices = new ApiServices();
        AtomicReference<CartViewModel> cartViewModel = new AtomicReference<>();
        apiServices.getCart(accessToken, cartId, c -> {
                    cart.set(c);
                    cartViewModel.set(new CartViewModel(c)); // new ViewModelProvider(this).get(CartViewModel.class);
                },
                e -> {
                    cartViewModel.set(new CartViewModel(new Cart(0, new ArrayList<>())));
                });

        RecyclerView recyclerView = root.findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CartAdapter adapter = new CartAdapter(getActivity(), cartViewModel.get(), accessToken);
        recyclerView.setAdapter(adapter);

        Button button = root.findViewById(R.id.checkoutButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PaymentActivity.class);
            intent.putExtra(CURRENT_USER_FOR_PAYMENT, user);
            intent.putExtra(CURRENT_CART_FOR_PAYMENT, cart.get());
            intent.putExtra(ACCESS_TOKEN_FOR_PAYMENT, accessToken);

            intentLauncher.launch(intent);
        });

        return root;
    }
}