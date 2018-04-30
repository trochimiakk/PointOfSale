package com.example.devices.printer;

import com.example.products.Product;

import java.util.List;

public interface Printer {

    void printReceipt(List<Product> productList, float totalPrice);

}
