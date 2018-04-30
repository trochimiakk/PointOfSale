package com.example.system;

import com.example.dao.ProductDao;
import com.example.devices.lcd.LcdDisplay;
import com.example.devices.printer.Printer;
import com.example.devices.scanner.BarcodeScanner;
import com.example.exceptions.EmptyProductListException;
import com.example.exceptions.InvalidBarcodeException;
import com.example.exceptions.ProductNotFoundException;
import com.example.products.Product;
import com.example.products.ProductList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PointOfSaleTest {

    @Mock
    BarcodeScanner barcodeScanner;

    @Mock
    LcdDisplay lcdDisplay;

    @Mock
    Printer printer;

    @Mock
    ProductDao productDao;

    @Mock
    ProductList productList;

    PointOfSale pointOfSale;

    @Before
    public void setUp() {
        pointOfSale = new PointOfSale(barcodeScanner, printer, lcdDisplay, productDao, productList);
    }

    @Test
    public void shouldReturnBarcode() throws InvalidBarcodeException {
        //given
        String expectedBarcode = "12";
        Mockito.when(barcodeScanner.scanBarcode()).thenReturn(expectedBarcode);

        //when
        String barcode = pointOfSale.scanBarcode();

        //then
        Assert.assertEquals(expectedBarcode, barcode);
        Mockito.verify(barcodeScanner, Mockito.times(1)).scanBarcode();

    }

    @Test(expected = InvalidBarcodeException.class)
    public void shouldThrowExceptionWhenBarcodeIsEmpty() throws InvalidBarcodeException {

        //given
        String emptyBarcode = "";
        Mockito.when(barcodeScanner.scanBarcode()).thenReturn(emptyBarcode);

        //when
        pointOfSale.scanBarcode();


    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenProductWasNotFoundInDatabase() throws ProductNotFoundException {

        //given
        Mockito.when(productDao.findProduct(Matchers.anyString())).thenThrow(ProductNotFoundException.class);

        //when
        pointOfSale.addProductToProductList(Matchers.anyString());

    }

    @Test
    public void shouldDisplayProductOnLcdAndAddProductToCustomerProductList() throws ProductNotFoundException {

        //given
        String barcode = "4";
        Product product = new Product("4", "Product #4", 1.0f);
        Mockito.when(productDao.findProduct(Matchers.anyString())).thenReturn(product);

        //when
        pointOfSale.addProductToProductList(barcode);

        //then
        Mockito.verify(productDao, Mockito.times(1)).findProduct(barcode);
        Mockito.verify(lcdDisplay, Mockito.times(1)).displayProduct(product);
        Mockito.verify(productList, Mockito.times(1)).add(product);

    }

    @Test
    public void shouldDisplayErrorMessage() {

        //given
        String errorMessage = "Error";

        //when
        pointOfSale.displayError(errorMessage);

        //then
        Mockito.verify(lcdDisplay, Mockito.times(1)).displayError(errorMessage);

    }

    @Test(expected = EmptyProductListException.class)
    public void shouldThrowExceptionWhenProductListIsEmpty() throws EmptyProductListException {

        //given
        Mockito.when(productList.isEmpty()).thenReturn(true);

        //when
        pointOfSale.summarizeTransaction();

    }

    @Test
    public void shouldPrintReceiptDisplayTotalPriceAndClearProductList() throws EmptyProductListException {

        //given
        float totalPrice = 10.5f;
        List<Product> products = Arrays.asList(new Product("1", "Product #1", 10.5f));
        Mockito.when(productList.calculateTotalPrice()).thenReturn(totalPrice);
        Mockito.when(productList.getProducts()).thenReturn(products);

        //when
        pointOfSale.summarizeTransaction();

        //then
        Mockito.verify(productList, Mockito.times(1)).calculateTotalPrice();
        Mockito.verify(productList, Mockito.times(1)).getProducts();
        Mockito.verify(lcdDisplay, Mockito.times(1)).displayTotalPrice(totalPrice);
        Mockito.verify(printer, Mockito.times(1)).printReceipt(products, totalPrice);

    }
}
