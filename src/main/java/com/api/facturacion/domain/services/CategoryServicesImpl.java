package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryDTO;
import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import com.api.facturacion.domain.models.Category;
import com.api.facturacion.domain.repository.CategoryRepository;
import com.api.facturacion.infrastructure.exceptions.categoryExceptions.CategoriesNotExistsException;
import com.api.facturacion.infrastructure.exceptions.categoryExceptions.CategoryExistException;
import com.api.facturacion.infrastructure.exceptions.categoryExceptions.CategoryNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryDTO categoryDTO) throws Exception {
        String categoryName = categoryDTO.categoryName().toLowerCase();
        Category category = getCategoryByName(categoryName);
        if (category == null){
            category = categoryRepository.save(new Category(categoryName));
            return new CategoryResponseDTO(category.getId(), category.getCategoryName());
        }else {
            throw new CategoryExistException(categoryDTO.categoryName());
        }
    }
    @Override
    public List<CategoryResponseDTO> listCategories() throws Exception {
        List<Category> categoryList = categoryRepository.findAll();

        if(categoryList.isEmpty()){
            throw new CategoriesNotExistsException();
        }

        return categoryList.stream().map(
                r -> new CategoryResponseDTO(r.getId(), r.getCategoryName())).toList();
    }
    @Override
    public Category getCategoryByName(String name) throws Exception {
        String categoryName = name.toLowerCase();
        return categoryRepository.findByCategoryName(categoryName);
    }
    @Override
    public CategoryResponseDTO getCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotExistsException(id));

        return new CategoryResponseDTO(category.getId(), category.getCategoryName());
    }
    @Override
    public CategoryResponseDTO deleteCategory(Long id) throws Exception {
        try {
            CategoryResponseDTO category = getCategory(id);
            categoryRepository.deleteById(id);

            return category;

        }catch (EmptyResultDataAccessException e){
            throw new CategoryNotExistsException(id);

        }
    }
}
