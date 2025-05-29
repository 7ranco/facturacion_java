package com.api.facturacion.domain.repository;

import com.api.facturacion.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCc(Long cc);

    Optional<Object> findByPhone(String phone);

    Optional<Object> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE c.cc = :cc")
    void deleteByCc(Long cc);
}
