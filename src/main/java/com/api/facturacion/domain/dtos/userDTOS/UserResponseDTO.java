package com.api.facturacion.domain.dtos.userDTOS;

import com.api.facturacion.domain.dtos.rolDTOS.RolResponseDTO;

public record UserResponseDTO(
        Long id,
        Long cc,
        String name,
        String lastname,
        String phone,
        String email,
        String password,
        RolResponseDTO rol
) {
}
