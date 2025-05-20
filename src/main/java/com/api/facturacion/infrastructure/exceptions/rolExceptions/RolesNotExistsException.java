package com.api.facturacion.infrastructure.exceptions.rolExceptions;

public class RolesNotExistsException extends RuntimeException{
    public RolesNotExistsException(){
        super("Any rol has registered yet");
    }
}
