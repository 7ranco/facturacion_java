package com.api.facturacion.infrastructure.exceptions.clientExceptions;

public class ClientsNotExistsException extends RuntimeException{
    public ClientsNotExistsException(){
        super("Any client is registered yet");
    }
}
