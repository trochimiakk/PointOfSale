package com.example.devices.scanner;

import java.util.Scanner;

public class ConsoleBarcodeScanner implements BarcodeScanner {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String scanBarcode() {
        String barcode = scanner.nextLine();
        return barcode;
    }
}
