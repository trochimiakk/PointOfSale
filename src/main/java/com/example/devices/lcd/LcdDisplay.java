package com.example.devices.lcd;

import com.example.products.Product;

public interface LcdDisplay {

    void displayProduct(Product product);

    void displayError(String message);

    void displayTotalPrice(float totalPrice);

}
