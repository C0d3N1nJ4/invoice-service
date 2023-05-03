package com.service.exceptions;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException(Long id) {
        super("Could not find invoice " + id);
    }
}
