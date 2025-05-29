package com.api.facturacion.domain.dtos.productDTOS;

public record ProductDTO(
        Long productCode,
        String productName,
        String description,
        Long stock,
        float unitPrice,
        Long categoryId
) {
}
