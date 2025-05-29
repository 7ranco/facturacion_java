package com.api.facturacion.infrastructure.exceptions.productExceptions;

public class ProductExistsException extends RuntimeException{

    public ProductExistsException(Long id){
        super("A Product with code: " + id + " already exists");
    }
}
