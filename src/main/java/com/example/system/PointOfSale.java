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

public class PointOfSale {

    private BarcodeScanner barcodeScanner;
    private Printer printer;
    private LcdDisplay lcdDisplay;
    private ProductDao productDao;
    private ProductList customerProductList;

    public PointOfSale(BarcodeScanner barcodeScanner, Printer printer, LcdDisplay lcdDisplay, ProductDao productDao, ProductList customerProductList) {
        this.barcodeScanner = barcodeScanner;
        this.printer = printer;
        this.lcdDisplay = lcdDisplay;
        this.productDao = productDao;
        this.customerProductList = customerProductList;
    }

    public String scanBarcode() throws InvalidBarcodeException {
        String barcode = barcodeScanner.scanBarcode();
        if (barcode.equals("")){
            throw new InvalidBarcodeException("Barcode is empty");
        }
        return barcode;
    }

    public void addProductToProductList(String barcode) throws ProductNotFoundException {
        Product product = productDao.findProduct(barcode);
        lcdDisplay.displayProduct(product);
        customerProductList.add(product);
    }

    public void displayError(String message){
        lcdDisplay.displayError(message);
    }

    public void summarizeTransaction() throws EmptyProductListException {
        if (customerProductList.isEmpty()){
            throw new EmptyProductListException("Product list is empty");
        }
        float totalPrice = customerProductList.calculateTotalPrice();
        lcdDisplay.displayTotalPrice(totalPrice);
        printer.printReceipt(customerProductList.getProducts(), totalPrice);
        customerProductList.clearProductList();
    }


}
