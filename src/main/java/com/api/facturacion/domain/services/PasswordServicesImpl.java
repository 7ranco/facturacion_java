package com.api.facturacion.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServicesImpl implements PasswordServices{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String encryptPasswords(String pass) throws Exception {
        return passwordEncoder.encode(pass);
    }

    @Override
    public boolean ValidatePasswords(String passOne, String PassTwo) throws Exception {
        return passwordEncoder.matches(passOne, PassTwo);
    }
}
