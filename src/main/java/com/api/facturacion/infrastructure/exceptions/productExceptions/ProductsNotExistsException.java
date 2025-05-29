package com.api.facturacion.infrastructure.exceptions.productExceptions;

public class ProductsNotExistsException extends RuntimeException{
    public ProductsNotExistsException(){
        super("Any products registered yet");
    }
}
