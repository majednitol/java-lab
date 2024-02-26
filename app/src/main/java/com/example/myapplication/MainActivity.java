package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;
    private TextView orderDetailsTextView, totalPriceTextView;
    private EditText feedbackEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderDetailsTextView = findViewById(R.id.orderDetailsTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        feedbackEditText = findViewById(R.id.feedbackEditText);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);
    }

    public void addProduct(View view) {
        // Create a dialog for user input
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Product");

        // Set up the input
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_product, null);
        builder.setView(dialogView);

        EditText productNameEditText = dialogView.findViewById(R.id.productNameEditText);
        EditText productPriceEditText = dialogView.findViewById(R.id.productPriceEditText);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get product name and price from EditTexts
                String productName = productNameEditText.getText().toString();
                int productPrice = Integer.parseInt(productPriceEditText.getText().toString());

                // Create a new Product object
                Product product = new Product(productName, productPrice);
                productList.add(product);
                productAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Show the dialog
        builder.show();
    }

    public void calculateTotalPrice(View view) {
        int totalPrice = 0;
        StringBuilder orderDetails = new StringBuilder();

        for (Product product : productList) {
            int productTotalPrice = product.getPrice() * product.getQuantity();
            totalPrice += productTotalPrice;

            orderDetails.append(product.getName()).append(" - ")
                    .append(product.getQuantity()).append(" x $").append(product.getPrice())
                    .append(" = $").append(productTotalPrice).append("\n");
        }

        orderDetailsTextView.setText(orderDetails.toString());
        totalPriceTextView.setText("Total Price: $" + totalPrice);
    }
}
