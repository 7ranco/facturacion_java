package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.rolDTOS.RolDTO;
import com.api.facturacion.domain.dtos.rolDTOS.RolResponseDTO;
import com.api.facturacion.domain.models.Rol;
import com.api.facturacion.domain.repository.RolRepository;
import com.api.facturacion.infrastructure.exceptions.rolExceptions.RolExistsException;
import com.api.facturacion.infrastructure.exceptions.rolExceptions.RolesNotExistsException;
import com.api.facturacion.infrastructure.exceptions.rolExceptions.RolNotExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RolServicesImpl implements RolServices {

    @Autowired
    private RolRepository rolRepository;


    @Override
    public RolResponseDTO createRol(@Valid RolDTO rolDTO) throws Exception {
        String rolName = rolDTO.rolName().toLowerCase();
        Rol rol = getRolByName(rolName);
        if (rol == null){
            rol = rolRepository.save(new Rol(rolName));
            return new RolResponseDTO(rol.getId(), rol.getRolName());
        }else{
            throw new RolExistsException(rol.getRolName());
        }
    }

    @Override
    public List<RolResponseDTO> listRoles(){
        List<Rol> listRol = rolRepository.findAll();

        if(listRol.isEmpty()){
            throw new RolesNotExistsException();
        }
        return listRol.stream().map(
                r -> new RolResponseDTO(r.getId(),r.getRolName())).toList();
    }
    @Override
    public Rol getRolByName(String rolName) throws Exception {
        return rolRepository.findByRolName(rolName);
    }

    @Override
    public RolResponseDTO getRol(Long id) throws Exception {
        Rol rol = getRolEntity(id);

        return new RolResponseDTO(rol.getId(), rol.getRolName());
    }

    @Override
    public Rol getRolEntity(Long id) throws Exception {
        return rolRepository.findById(id).orElseThrow(() -> new RolNotExistsException(id));
    }

    @Override
    public RolResponseDTO deleteRols(Long id) throws Exception {
        try{
            RolResponseDTO rol = getRol(id);
            rolRepository.deleteById(id);

            return rol;
        } catch (EmptyResultDataAccessException e) {
            throw new RolNotExistsException(id);
        }
    }
}
