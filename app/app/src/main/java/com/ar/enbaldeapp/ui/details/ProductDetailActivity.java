package com.ar.enbaldeapp.ui.details;

import static com.ar.enbaldeapp.ui.IntentConstants.PRODUCT_FOR_DETAIL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        product = (Product)intent.getSerializableExtra(PRODUCT_FOR_DETAIL);

        ((TextView)this.findViewById(R.id.textViewDetailName)).setText(product.getName());
        ((TextView)this.findViewById(R.id.textViewDetailDescription)).setText(product.getDescription());
        ((TextView)this.findViewById(R.id.editNumberDetailAmount)).setText("1");
    }
}