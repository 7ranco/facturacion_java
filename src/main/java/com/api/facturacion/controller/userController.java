package com.api.facturacion.controller;


import com.api.facturacion.domain.dtos.userDTOS.UserDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO,
                                        UriComponentsBuilder uriComponentsBuilder){
        try{
            UserResponseDTO user = userServices.createUser(userDTO);
            URI uri = uriComponentsBuilder.path("/user/{id}")
                    .buildAndExpand(user.id())
                    .toUri();

            return ResponseEntity.created(uri).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listUsers(){
        try{
            List<UserResponseDTO> user = userServices.listUsers();

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{cc}")
    public ResponseEntity<?> getUser(@PathVariable Long cc){
        try{
            UserResponseDTO getUser = userServices.getUser(cc);

            return ResponseEntity.ok(getUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            UserResponseDTO userDeleted = userServices.deleteUser(id);
            return ResponseEntity.ok(userDeleted);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


}
