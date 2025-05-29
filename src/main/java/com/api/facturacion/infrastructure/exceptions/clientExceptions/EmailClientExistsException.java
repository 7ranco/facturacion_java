package com.api.facturacion.infrastructure.exceptions.clientExceptions;

public class EmailClientExistsException extends RuntimeException {
    public EmailClientExistsException(String email) {
        super("A client with email " +email+ " is registered");
    }
}
