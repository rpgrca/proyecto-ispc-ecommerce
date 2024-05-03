package com.ar.enbaldeapp.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.Product;

import java.util.List;

public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.ViewHolder> {
    private CatalogueAdapter.OnClickListener onProductClickListener;
    private final List<Product> products;

    public CatalogueAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogue_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)  {
        Product product = products.get(position);

        holder.productImageView.setImageResource(getImageResourceByName(product.getImage(), holder.itemView.getContext()));
        holder.productImageView.setOnClickListener(v -> {
            if (onProductClickListener != null) {
                onProductClickListener.onClick(position, product);
            }
        });

        holder.productNameTextView.setText(product.getName());
        holder.productNameTextView.setOnClickListener(v -> {
            if (onProductClickListener != null) {
                onProductClickListener.onClick(position, product);
            }
        });

        holder.productDescriptionTextView.setText(product.getDescription());
        holder.productDescriptionTextView.setOnClickListener(v -> {
            if (onProductClickListener != null) {
                onProductClickListener.onClick(position, product);
            }
        });
    }

    public interface OnClickListener {
        void onClick(int position, Product product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setOnClickListeners(CatalogueAdapter.OnClickListener onClickListener) {
        this.onProductClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;
        TextView productDescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
        }
    }

    private int getImageResourceByName(String imageName, Context context) {
        return R.drawable.baseline_icecream_24;
    }
}
