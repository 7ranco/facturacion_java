package com.api.facturacion.domain.repository;

import com.api.facturacion.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String name);
}
