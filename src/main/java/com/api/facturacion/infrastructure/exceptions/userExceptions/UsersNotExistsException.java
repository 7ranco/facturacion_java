package com.api.facturacion.infrastructure.exceptions.userExceptions;


public class UsersNotExistsException extends RuntimeException{
    public UsersNotExistsException(){
        super("Don´t exists users registered");
    }
}
