package com.api.facturacion.infrastructure.exceptions.userExceptions;

public class ErrorPasswordEmailException extends RuntimeException{
    public ErrorPasswordEmailException(){
        super("The email or password typing is not correct");
    }
}
