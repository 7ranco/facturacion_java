package com.api.facturacion.domain.models;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryDTO;
import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    public Category(){
    }

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public Category(CategoryResponseDTO category){
        this.id = category.id();
        this.categoryName = category.categoryName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
