package com.example.products;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductListTest {

    ProductList productList;

    @Before
    public void setUp() {
        productList = new ProductList();
    }

    @Test
    public void shouldCalculateTotalPrice() {

        //given
        float expectedTotalPrice = 15.1f;
        Product prod1 = new Product("1", "Product #1", 7.3f);
        Product prod2 = new Product("2", "Product #2", 4.2f);
        Product prod3 = new Product("3", "Product #3", 3.6f);
        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);

        //when
        float calculatedTotalPrice = productList.calculateTotalPrice();

        //then
        Assert.assertEquals(expectedTotalPrice, calculatedTotalPrice, 0.0f);

    }
}
