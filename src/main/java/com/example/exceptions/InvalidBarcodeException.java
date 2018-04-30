package com.example.exceptions;

public class InvalidBarcodeException extends Exception {

    public InvalidBarcodeException(String message) {
        super(message);
    }
}
