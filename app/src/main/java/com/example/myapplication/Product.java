package com.example.myapplication;

public class Product {
    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
