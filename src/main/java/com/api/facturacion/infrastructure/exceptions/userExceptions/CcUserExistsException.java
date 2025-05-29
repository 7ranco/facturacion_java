package com.api.facturacion.infrastructure.exceptions.userExceptions;

public class CcUserExistsException extends RuntimeException{
    public CcUserExistsException(Long cc){
        super("A User with CC " + cc + " is registered");
    }

}
