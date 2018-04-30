package com.example.devices.printer;

import com.example.products.Product;

import java.util.List;

;

public class ConsolePrinter implements Printer {

    @Override
    public void printReceipt(List<Product> productList, float totalPrice) {
        System.out.println("Printer - Receipt:");
        productList.forEach(product -> {
            System.out.format("%s %.2f %n", product.getName(), product.getPrice());
        });
        System.out.format("Total price: %.2f %n", totalPrice);
    }
}
