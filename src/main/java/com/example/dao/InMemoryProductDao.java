package com.example.dao;

import com.example.exceptions.ProductNotFoundException;
import com.example.products.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductDao implements ProductDao{

    private List<Product> products = new ArrayList<>();


    @Override
    public Product findProduct(String barcode) throws ProductNotFoundException {

        Product product = products.stream().filter(prod -> prod.getBarcode().equals(barcode))
                .findAny().orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return product;

    }

    @Override
    public void saveProduct(Product product) {
        products.add(product);
    }
}
