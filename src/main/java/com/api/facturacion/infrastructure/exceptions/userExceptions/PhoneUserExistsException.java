package com.api.facturacion.infrastructure.exceptions.userExceptions;

public class PhoneUserExistsException extends RuntimeException{
    public PhoneUserExistsException(String phone){
        super("A User with phone " + phone + " is registered");
    }

}
