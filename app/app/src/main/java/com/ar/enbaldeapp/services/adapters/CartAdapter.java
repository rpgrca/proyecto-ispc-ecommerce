package com.ar.enbaldeapp.services.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.Selection;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.cart.CartViewModel;
import com.squareup.picasso.Picasso;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Activity activityContext;
    private CartAdapter.OnClickListener onSelectionClickListener;
    private final CartViewModel cart;

    public CartAdapter(Activity activity, CartViewModel cart) {
        this.activityContext = activity;
        this.cart = cart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)  {
        Selection selection = cart.getSelections().getValue().get(position);

        holder.bindContent(selection);

        holder.cartProductImageView.setOnClickListener(v -> {
            if (onSelectionClickListener != null) {
                onSelectionClickListener.onClick(position, selection);
            }
        });

        holder.cartProductNameTextView.setText(selection.getProduct().getName());
        holder.cartProductNameTextView.setOnClickListener(v -> {
            if (onSelectionClickListener != null) {
                onSelectionClickListener.onClick(position, selection);
            }
        });

        holder.cartProductQuantityTextView.setText(" " + selection.getQuantity() + " ");
        holder.cartProductQuantityTextView.setOnClickListener(v -> {
            if (onSelectionClickListener != null) {
                onSelectionClickListener.onClick(position, selection);
            }
        });

        holder.cartProductSubTotalTextView.setText("Subtotal: " + selection.getTotal());
        holder.cartProductSubTotalTextView.setOnClickListener(v -> {
            if (onSelectionClickListener != null) {
                onSelectionClickListener.onClick(position, selection);
            }
        });
    }

    public interface OnClickListener {
        void onClick(int position, Selection selection);
    }

    @Override
    public int getItemCount() {
        return cart.getSelections().getValue().size();
    }

    public void setOnClickListeners(CartAdapter.OnClickListener onClickListener) {
        this.onSelectionClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cartProductImageView;
        TextView cartProductNameTextView;
        TextView cartProductQuantityTextView;
        TextView cartProductSubTotalTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductImageView = itemView.findViewById(R.id.cartProductImage);
            cartProductNameTextView = itemView.findViewById(R.id.cartProductName);
            cartProductQuantityTextView = itemView.findViewById(R.id.cartQuantity);
            cartProductSubTotalTextView = itemView.findViewById(R.id.cartSubtotal);
        }

        public void bindContent(Selection selection) {
            IApiServices apiServices = new ApiServices();
            Picasso.with(activityContext).load(apiServices.getUrl() + selection.getProduct().getImage()).into(cartProductImageView);
        }
    }
}
