package com.api.facturacion.domain.models;

import com.api.facturacion.domain.dtos.rolDTOS.RolDTO;
import com.api.facturacion.domain.dtos.rolDTOS.RolResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Data
@Table(name = "rols")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rolName;
    public Rol(){}

    public Rol(String rolName){
        this.rolName = rolName;
    }

    public Rol(RolResponseDTO rol){
        this.id = rol.id();
        this.rolName = rol.rolName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
}
