package com.api.facturacion.infrastructure.exceptions.rolExceptions;

public class RolNotExistsException extends RuntimeException{
    public RolNotExistsException(Long id){
        super("A rol with id " + id + " is not registered");
    }
}
