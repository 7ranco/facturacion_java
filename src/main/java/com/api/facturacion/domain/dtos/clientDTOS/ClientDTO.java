package com.api.facturacion.domain.dtos.clientDTOS;

import com.api.facturacion.domain.models.Adress;
import jakarta.persistence.Embedded;

public record ClientDTO(
    Long cc,
    String name,
    String lastname,
    String email,
    String phone,
    AdressDTO adress

) {
}