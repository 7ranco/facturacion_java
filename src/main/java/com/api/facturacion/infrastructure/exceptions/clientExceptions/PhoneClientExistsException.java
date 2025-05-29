package com.api.facturacion.infrastructure.exceptions.clientExceptions;

public class PhoneClientExistsException extends RuntimeException {
    public PhoneClientExistsException(String phone) {
        super("A Client with phone " +phone+ " is registered");
    }
}
