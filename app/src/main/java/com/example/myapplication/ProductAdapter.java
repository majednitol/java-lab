package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView, productPriceTextView, productQuantityTextView;
        private Button incrementButton, decrementButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            incrementButton = itemView.findViewById(R.id.incrementButton);
            decrementButton = itemView.findViewById(R.id.decrementButton);
        }

        public void bind(Product product) {
            productNameTextView.setText(product.getName());
            productPriceTextView.setText("$" + product.getPrice());
            productQuantityTextView.setText(String.valueOf(product.getQuantity()));

            incrementButton.setOnClickListener(v -> {
                product.setQuantity(product.getQuantity() + 1);
                productQuantityTextView.setText(String.valueOf(product.getQuantity()));
            });

            decrementButton.setOnClickListener(v -> {
                int quantity = product.getQuantity();
                if (quantity > 0) {
                    product.setQuantity(quantity - 1);
                    productQuantityTextView.setText(String.valueOf(product.getQuantity()));
                }
            });
        }
    }
}
