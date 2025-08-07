package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.loginDTOS.LoginDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;

public interface LoginServices {
    UserResponseDTO validateUser(LoginDTO loginDTO) throws Exception;

}
