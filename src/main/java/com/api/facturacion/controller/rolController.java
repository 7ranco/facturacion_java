package com.api.facturacion.controller;

import com.api.facturacion.domain.dtos.rolDTOS.RolDTO;
import com.api.facturacion.domain.dtos.rolDTOS.RolResponseDTO;
import com.api.facturacion.domain.services.RolServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rol")
public class rolController {

    @Autowired
    private RolServices rolServices;
    @PostMapping
    public ResponseEntity<?> createRol(@RequestBody @Valid RolDTO rolDTO,
                                       UriComponentsBuilder uriComponentsBuilder){
        try{
            RolResponseDTO rol = rolServices.createRol(rolDTO);

            URI uri = uriComponentsBuilder.path("/rol/{id}")
                    .buildAndExpand(rol.id())
                    .toUri();

            return ResponseEntity.created(uri).body(rol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listRols(){
        try{
            List<RolResponseDTO> rol = rolServices.listRoles();
            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRol(@PathVariable Long id){
        try{
            RolResponseDTO rol = rolServices.getRol(id);
            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id){
        try{
            RolResponseDTO rol = rolServices.deleteRols(id);
            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
