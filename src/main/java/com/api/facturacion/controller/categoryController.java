package com.api.facturacion.controller;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryDTO;
import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import com.api.facturacion.domain.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class categoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDTO categoryDTO,
                                            UriComponentsBuilder uriComponentsBuilder){
        try{
            CategoryResponseDTO category = categoryServices.createCategory(categoryDTO);
            URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(category.id())
                    .toUri();

            return ResponseEntity.created(uri).body(category);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
    @GetMapping
    public ResponseEntity<?> listCategories(){
        try{
            List<CategoryResponseDTO> category = categoryServices.listCategories();

            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        try{
            CategoryResponseDTO getCategory = categoryServices.getCategory(id);
            return ResponseEntity.ok(getCategory);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        try{
            CategoryResponseDTO categoryDeleted = categoryServices.deleteCategory(id);
            return ResponseEntity.ok(categoryDeleted);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
