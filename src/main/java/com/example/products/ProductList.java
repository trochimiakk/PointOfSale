package com.example.products;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    private List<Product> products = new ArrayList<>();;

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product){
        products.add(product);
    }

    public float calculateTotalPrice(){
        float totalPrice = (float) products.stream().mapToDouble(Product::getPrice).sum();
        return totalPrice;
    }

    public void clearProductList(){
        products.clear();
    }

    public boolean isEmpty(){
        return products.size() == 0;
    }
}
