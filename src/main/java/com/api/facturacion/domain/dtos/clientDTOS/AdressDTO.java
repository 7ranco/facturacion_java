package com.api.facturacion.domain.dtos.clientDTOS;

public record AdressDTO(

        String city,
        String neighborhood,

        String street,

        String complement,

        String details
) {
}
