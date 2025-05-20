package com.api.facturacion.infrastructure.exceptions.rolExceptions;

public class RolExistsException extends RuntimeException{
    public RolExistsException(String name){
        super("A rol with name " + name + " already exists");
    }
}
