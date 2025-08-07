package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.userDTOS.UserDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.models.User;

import java.util.List;

public interface UserServices {
    UserResponseDTO createUser(UserDTO userDTO) throws Exception;

    List<UserResponseDTO> listUsers() throws Exception;

    UserResponseDTO getUser(Long cc) throws Exception;
    User getUserEntity(Long cc) throws Exception;

    UserResponseDTO deleteUser(Long id) throws Exception;

}
