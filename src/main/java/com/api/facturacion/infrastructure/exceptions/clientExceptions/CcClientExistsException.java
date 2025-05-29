package com.api.facturacion.infrastructure.exceptions.clientExceptions;

public class CcClientExistsException extends RuntimeException{
    public CcClientExistsException(Long cc){
        super("A client with CC " + cc + "is registered" );
    }
}
