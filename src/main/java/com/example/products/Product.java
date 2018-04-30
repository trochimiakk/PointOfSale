package com.example.products;

public class Product {

    private String barcode;
    private String name;
    private float price;

    public Product(String barcode, String name, float price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
