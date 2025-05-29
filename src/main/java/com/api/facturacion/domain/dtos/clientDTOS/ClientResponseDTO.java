package com.api.facturacion.domain.dtos.clientDTOS;

public record ClientResponseDTO(
        Long id,
        Long cc,
        String name,
        String lastname,
        String email,
        String phone,
        AdressDTO adress

) {
}
