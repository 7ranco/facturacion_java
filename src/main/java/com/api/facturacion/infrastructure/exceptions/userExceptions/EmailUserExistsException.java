package com.api.facturacion.infrastructure.exceptions.userExceptions;

public class EmailUserExistsException extends RuntimeException{
    public EmailUserExistsException(String email){
        super("A User with Email " + email + " is registered");
    }

}
