package com.api.facturacion.infrastructure.exceptions.userExceptions;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(Long id){
        super("A user with ID " + id + " not exist");
    }
}
