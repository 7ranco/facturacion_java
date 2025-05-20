package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.RolDTO;
import com.api.facturacion.domain.dtos.RolResponseDTO;
import com.api.facturacion.domain.models.Rol;
import jakarta.validation.Valid;

import java.util.List;

public interface RolServices {
    RolResponseDTO createRol(@Valid RolDTO rolDTO) throws Exception;

    Rol getRolByName(RolDTO rolDTO) throws Exception;

    List<RolResponseDTO> listRoles() throws Exception;

    RolResponseDTO getRol(Long id) throws Exception;

    RolResponseDTO deleteRols(Long id) throws Exception;
}
