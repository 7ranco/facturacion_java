package com.api.facturacion.controller;

import com.api.facturacion.domain.dtos.productDTOS.ProductDTO;
import com.api.facturacion.domain.dtos.productDTOS.ProductResponseDTO;
import com.api.facturacion.domain.services.ProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO,
                                           UriComponentsBuilder uriComponentsBuilder){
        try{
            ProductResponseDTO product = productServices.createProduct(productDTO);
            URI uri = uriComponentsBuilder.path("/product/{id}")
                    .buildAndExpand(product.id())
                    .toUri();

            return ResponseEntity.created(uri).body(product);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listProducts(){
        try{
            List<ProductResponseDTO> product = productServices.listProducts();

            return ResponseEntity.ok(product);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getProduct(@PathVariable Long code){
        try{
            ProductResponseDTO product = productServices.getProduct(code);

            return ResponseEntity.ok(product);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long code){
        try{
            ProductResponseDTO product = productServices.deleteProduct(code);

            return ResponseEntity.ok(product);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
