package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;

public interface LoginServices {
    UserResponseDTO validateUser(String email, String password) throws Exception;

}
