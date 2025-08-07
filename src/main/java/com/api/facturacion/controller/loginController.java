package com.api.facturacion.controller;

import com.api.facturacion.domain.dtos.loginDTOS.LoginDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.services.LoginServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
public class loginController {

    @Autowired
    private LoginServices loginServices;

    @PostMapping
    public ResponseEntity<?> validateUser(@RequestBody @Valid LoginDTO loginDTO){
        try{

            UserResponseDTO userResponseDTO = loginServices.validateUser(loginDTO);

            return ResponseEntity.ok(userResponseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }
}
