package com.api.facturacion.infrastructure.exceptions.categoryExceptions;


public class CategoryExistException extends RuntimeException{
    public CategoryExistException(String name){
        super("A category with name " +name+ " already exists");
    }
}
