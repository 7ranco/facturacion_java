package com.api.facturacion.infrastructure.exceptions.clientExceptions;

public class ClientNotExistsException extends RuntimeException {
    public ClientNotExistsException(Long cc){
        super("A client with cc: " +cc+ " is not registered");
    }
}
