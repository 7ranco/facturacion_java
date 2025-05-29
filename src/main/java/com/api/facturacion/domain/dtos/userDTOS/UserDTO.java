package com.api.facturacion.domain.dtos.userDTOS;

import com.api.facturacion.domain.models.Rol;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record UserDTO(
        Long cc,
        String name,
        String lastname,
        String phone,
        String email,
        String password,
        Long rolId
) {
}
