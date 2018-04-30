package com.example.exceptions;

public class EmptyProductListException extends Exception {

    public EmptyProductListException(String message) {
        super(message);
    }
}
