package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.productDTOS.ProductDTO;
import com.api.facturacion.domain.dtos.productDTOS.ProductResponseDTO;

import java.util.List;

public interface ProductServices {
    ProductResponseDTO createProduct(ProductDTO productDTO) throws Exception;

    List<ProductResponseDTO> listProducts() throws Exception;

    ProductResponseDTO getProduct(Long code) throws Exception;

    ProductResponseDTO deleteProduct(Long id) throws Exception;
}
