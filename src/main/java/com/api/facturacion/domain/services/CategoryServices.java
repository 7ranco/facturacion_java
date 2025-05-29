package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryDTO;
import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import com.api.facturacion.domain.models.Category;

import java.util.List;

public interface CategoryServices {
    CategoryResponseDTO createCategory(CategoryDTO categoryDTO) throws Exception;
    Category getCategoryByName(String name) throws Exception;
    List<CategoryResponseDTO> listCategories() throws Exception;
    CategoryResponseDTO getCategory(Long id) throws Exception;
    CategoryResponseDTO deleteCategory(Long id) throws Exception;

}
