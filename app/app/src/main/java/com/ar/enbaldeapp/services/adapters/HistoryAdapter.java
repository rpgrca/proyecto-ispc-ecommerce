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
import com.ar.enbaldeapp.models.Sale;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.history.HistoryViewModel;
import com.squareup.picasso.Picasso;

import java.util.stream.Collectors;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final Activity activityContext;
    private final HistoryViewModel history;

    public HistoryAdapter(Activity activity, HistoryViewModel history) {
        this.activityContext = activity;
        this.history = history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (history.getSales().getValue() != null) {
            Sale sale = history.getSales().getValue().get(position);

            holder.historyProductNameTextView.setText(getSaleName(sale));
            holder.bindContent(sale);
            holder.historyProductSubTotalTextView.setText(String.format("Total: $%s", sale.getTotal()));
            holder.historyProductPaymentTextView.setText(sale.getPaymentAsString());
            holder.historyProductShippingTextView.setText(sale.getShippingMethod());
        }
    }

    private String getSaleName(Sale sale) {
        String description = sale.getSelections().stream().map(p -> p.getQuantity() + " x " + p.getProduct().getName()).collect(Collectors.joining(", "));
        if (description.isEmpty()) {
            description = "Artículos varios";
        }

        return description;
    }

    @Override
    public int getItemCount() {
        if (history.getSales().getValue() != null) {
            return history.getSales().getValue().size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView historyProductImageView;
        TextView historyProductNameTextView;
        TextView historyProductPaymentTextView;
        TextView historyProductSubTotalTextView;
        TextView historyProductShippingTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyProductImageView = itemView.findViewById(R.id.historyProductImage);
            historyProductNameTextView = itemView.findViewById(R.id.historyProductName);
            historyProductPaymentTextView = itemView.findViewById(R.id.historyPayment);
            historyProductShippingTextView = itemView.findViewById(R.id.historyShipping);
            historyProductSubTotalTextView = itemView.findViewById(R.id.historySubtotal);
        }

        public void bindContent(Sale sale) {
            IApiServices apiServices = new ApiServices();
            if (! sale.getSelections().isEmpty()) {
                Picasso.with(activityContext).load(apiServices.getUrl() + sale.getSelections().get(0).getProduct().getImage()).into(historyProductImageView);
            }
        }
    }
}
