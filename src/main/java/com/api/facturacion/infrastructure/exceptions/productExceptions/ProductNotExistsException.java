package com.api.facturacion.infrastructure.exceptions.productExceptions;

public class ProductNotExistsException extends RuntimeException{

    public ProductNotExistsException(Long id){
        super("A Product with code: " + id + " is not registered");
    }
}
