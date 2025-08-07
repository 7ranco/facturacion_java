package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import com.api.facturacion.domain.dtos.productDTOS.ProductDTO;
import com.api.facturacion.domain.dtos.productDTOS.ProductResponseDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.models.Category;
import com.api.facturacion.domain.models.Product;
import com.api.facturacion.domain.repository.ProductRepository;
import com.api.facturacion.infrastructure.exceptions.categoryExceptions.CategoryNotExistsException;
import com.api.facturacion.infrastructure.exceptions.productExceptions.ProductExistsException;
import com.api.facturacion.infrastructure.exceptions.productExceptions.ProductNotExistsException;
import com.api.facturacion.infrastructure.exceptions.productExceptions.ProductsNotExistsException;
import com.api.facturacion.infrastructure.exceptions.rolExceptions.RolNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServices categoryServices;

    @Override
    public ProductResponseDTO createProduct(ProductDTO productDTO) throws Exception {
        CategoryResponseDTO category = categoryServices.getCategory(productDTO.categoryId());

        System.out.println(category);

        if(productRepository.findByProductCode(productDTO.productCode()).isPresent()){
            throw new ProductExistsException(productDTO.productCode());
        }

        if(category == null){
            throw new CategoryNotExistsException(productDTO.categoryId());
        }

        Product product = productRepository.save(new Product(productDTO, new Category(category)));

        return new ProductResponseDTO(product.getId(), product.getProductCode(), product.getProductName(),
                product.getDescription(), product.getStock(), product.getUnitPrice(), new CategoryResponseDTO(product.getCategory().getId(), product.getCategory().getCategoryName()));
    }

    @Override
    public List<ProductResponseDTO> listProducts() throws Exception {
        List<Product> productList = productRepository.findAll();

        if(productList.isEmpty()){
            throw new ProductsNotExistsException();
        }

        return productList.stream().map(
                product -> new ProductResponseDTO(product.getId(), product.getProductCode(), product.getProductName(),
                        product.getDescription(), product.getStock(), product.getUnitPrice(),
                        new CategoryResponseDTO(product.getCategory().getId(), product.getCategory().getCategoryName()))).toList();
    }

    @Override
    public ProductResponseDTO getProduct(Long code) throws Exception {
        Product product = getProductEntity(code);

        return new ProductResponseDTO(product.getId(), product.getProductCode(), product.getProductName(),
                product.getDescription(), product.getStock(), product.getUnitPrice(), new CategoryResponseDTO(product.getCategory().getId(), product.getCategory().getCategoryName()));
    }

    @Override
    public Product getProductEntity(Long code) throws Exception {
        return productRepository.findByProductCode(code).orElseThrow(() -> new ProductExistsException(code));
    }

    @Override
    public ProductResponseDTO deleteProduct(Long id) throws Exception {
        try{
            ProductResponseDTO user = getProduct(id);
            productRepository.deleteByProductCode(id);
            return user;
        }catch (EmptyResultDataAccessException e){
            throw new ProductNotExistsException(id);
        }
    }
}
