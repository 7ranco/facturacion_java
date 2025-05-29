package com.api.facturacion.domain.dtos.productDTOS;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import com.api.facturacion.domain.models.Category;

public record ProductResponseDTO(
        Long id,
        Long productCode,
        String productName,
        String description,
        Long stock,
        float unitPrice,
        CategoryResponseDTO category
) {
}
