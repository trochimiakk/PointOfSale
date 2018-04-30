package com.example.dao;

import com.example.exceptions.ProductNotFoundException;
import com.example.products.Product;

public interface ProductDao {

    Product findProduct(String barcode) throws ProductNotFoundException;
    void saveProduct(Product product);

}
