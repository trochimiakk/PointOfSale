package com.example.main;

import com.example.dao.InMemoryProductDao;
import com.example.dao.ProductDao;
import com.example.devices.lcd.ConsoleLcdDisplay;
import com.example.devices.lcd.LcdDisplay;
import com.example.devices.printer.ConsolePrinter;
import com.example.devices.printer.Printer;
import com.example.devices.scanner.BarcodeScanner;
import com.example.devices.scanner.ConsoleBarcodeScanner;
import com.example.exceptions.EmptyProductListException;
import com.example.exceptions.InvalidBarcodeException;
import com.example.exceptions.ProductNotFoundException;
import com.example.products.Product;
import com.example.products.ProductList;
import com.example.system.PointOfSale;

public class Main {

    public static void main(String[] args) {

        BarcodeScanner barcodeScanner = new ConsoleBarcodeScanner();
        LcdDisplay lcdDisplay = new ConsoleLcdDisplay();
        Printer printer = new ConsolePrinter();
        ProductDao productDao = new InMemoryProductDao();
        productDao.saveProduct(new Product("1", "Product #1", 2.0f));
        productDao.saveProduct(new Product("2", "Product #2", 4.2f));
        productDao.saveProduct(new Product("3", "Product #3", 1.5f));
        ProductList productList = new ProductList();

        PointOfSale pointOfSale = new PointOfSale(barcodeScanner, printer, lcdDisplay, productDao, productList);

        while (true){
            try {
                String barcode = pointOfSale.scanBarcode();
                if (!barcode.equals("exit")){
                    pointOfSale.addProductToProductList(barcode);
                } else{
                    pointOfSale.summarizeTransaction();
                }


            } catch (InvalidBarcodeException e) {
                pointOfSale.displayError("Invalid barcode");
            } catch (ProductNotFoundException e) {
                pointOfSale.displayError("Product not found");
            } catch (EmptyProductListException e) {
                pointOfSale.displayError("Product list is empty");
            }
        }

    }
}
