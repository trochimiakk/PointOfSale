package com.example.devices.lcd;

import com.example.products.Product;

public class ConsoleLcdDisplay implements LcdDisplay {

    @Override
    public void displayProduct(Product product) {
        System.out.format("LCD Display: %s %.2f %n", product.getName(), product.getPrice());
    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    @Override
    public void displayTotalPrice(float totalPrice) {
        System.out.format("LCD Display: Total price: %.2f %n", totalPrice);
    }
}
