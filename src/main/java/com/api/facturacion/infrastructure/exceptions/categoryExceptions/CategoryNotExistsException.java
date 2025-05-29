package com.api.facturacion.infrastructure.exceptions.categoryExceptions;

public class CategoryNotExistsException extends RuntimeException{
    public CategoryNotExistsException(Long id){
        super("A category with ID " +id+ " is not registered");
    }
}
