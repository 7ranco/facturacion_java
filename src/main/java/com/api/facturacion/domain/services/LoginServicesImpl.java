package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.rolDTOS.RolResponseDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.models.User;
import com.api.facturacion.domain.repository.UserRepository;
import com.api.facturacion.infrastructure.exceptions.userExceptions.EmailUserExistsException;
import com.api.facturacion.infrastructure.exceptions.userExceptions.ErrorPasswordEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServicesImpl implements LoginServices{

    @Autowired
    private PasswordServices passwordServices;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserResponseDTO validateUser(String email, String password) throws Exception {
        Optional<User> loginUser = userRepository.findByEmail(email);

        if (loginUser.isEmpty()) {
            throw new EmailUserExistsException(email);
        }

        User user = loginUser.get();

        if (passwordServices.ValidatePasswords(password, user.getPassword())) {
            return new UserResponseDTO(
                    user.getId(),
                    user.getCc(),
                    user.getName(),
                    user.getLastname(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getPassword(),
                    new RolResponseDTO(
                            user.getRol().getId(),
                            user.getRol().getRolName()
                    )
            );
        } else {
            throw new ErrorPasswordEmailException();
        }
    }
}
