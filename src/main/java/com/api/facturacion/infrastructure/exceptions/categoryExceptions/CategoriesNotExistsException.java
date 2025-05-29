package com.api.facturacion.infrastructure.exceptions.categoryExceptions;

public class CategoriesNotExistsException extends RuntimeException{

    public CategoriesNotExistsException(){
        super("Any category registered yet");
    }
}
