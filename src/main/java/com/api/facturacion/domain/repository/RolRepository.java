package com.api.facturacion.domain.repository;

import com.api.facturacion.domain.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<Rol, Long> {
    @Query("""
            select r from Rol r
                        where r.rolName = :rolname
            """)
    Rol findByRolName(String rolname);
}
