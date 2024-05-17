package com.ar.enbaldeapp.services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.Selection;
import com.ar.enbaldeapp.ui.cart.CartViewModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private CartAdapter.OnClickListener onSelectionClickListener;
    private final CartViewModel cart;

    public CartAdapter(CartViewModel cart) {
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

        holder.cartProductImageView.setImageResource(getImageResourceByName(selection.getProduct().getImage(), holder.itemView.getContext()));
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

        holder.cartProductDescriptionTextView.setText(selection.getProduct().getDescription());
        holder.cartProductDescriptionTextView.setOnClickListener(v -> {
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
        TextView cartProductDescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductImageView = itemView.findViewById(R.id.cartProductImage);
            cartProductNameTextView = itemView.findViewById(R.id.cartProductName);
            cartProductDescriptionTextView = itemView.findViewById(R.id.cartProductDescription);
        }
    }

    private int getImageResourceByName(String imageName, Context context) {
        return R.drawable.baseline_icecream_24;
    }
}
