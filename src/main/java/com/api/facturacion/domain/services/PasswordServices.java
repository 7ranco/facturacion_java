package com.api.facturacion.domain.services;

public interface PasswordServices {
    String encryptPasswords(String pass)throws Exception;
    boolean ValidatePasswords(String passOne, String PassTwo)throws Exception;
}
