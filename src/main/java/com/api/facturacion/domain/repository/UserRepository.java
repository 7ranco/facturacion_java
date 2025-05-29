package com.api.facturacion.domain.repository;

import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByCc(Long cc);
    Optional<User> findByPhone(String phone);

    @Query("""
        SELECT u FROM User u
        WHERE u.email = :email AND u.password = :password
        """)
    Optional<User> findByEmailPassword(@Param("email") String email, @Param("password") String password);


    void deleteByCc(Long cc);
}
