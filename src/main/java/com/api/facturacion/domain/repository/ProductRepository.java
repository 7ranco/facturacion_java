package com.api.facturacion.domain.repository;

import com.api.facturacion.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(Long code);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.productCode = :id")
    void deleteByProductCode(Long id);
}
