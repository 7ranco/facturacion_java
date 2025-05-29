package com.api.facturacion.controller;

import com.api.facturacion.domain.dtos.categoryDTOS.CategoryResponseDTO;
import com.api.facturacion.domain.dtos.clientDTOS.ClientDTO;
import com.api.facturacion.domain.dtos.clientDTOS.ClientResponseDTO;
import com.api.facturacion.domain.services.ClientServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class clientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDTO clientDTO,
                                          UriComponentsBuilder uriComponentsBuilder){
        try{
            ClientResponseDTO client = clientServices.createClient(clientDTO);

            URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.id()).toUri();

            return ResponseEntity.created(uri).body(client);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listClients(){
        try{
            List<ClientResponseDTO> client = clientServices.listClients();

            return ResponseEntity.ok(client);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{cc}")
    public ResponseEntity<?> getClient(@PathVariable Long cc){
        try{
            ClientResponseDTO client = clientServices.getClient(cc);
            return ResponseEntity.ok(client);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping("/{cc}")
    public ResponseEntity<?> deleteClient(@PathVariable Long cc){
        try{
            ClientResponseDTO client = clientServices.deleteClient(cc);
            return ResponseEntity.ok(client);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}
