package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.rolDTOS.RolResponseDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserDTO;
import com.api.facturacion.domain.dtos.userDTOS.UserResponseDTO;
import com.api.facturacion.domain.models.Rol;
import com.api.facturacion.domain.models.User;
import com.api.facturacion.domain.repository.UserRepository;
import com.api.facturacion.infrastructure.exceptions.rolExceptions.RolNotExistsException;
import com.api.facturacion.infrastructure.exceptions.userExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private RolServices rolServices;
    @Autowired
    private PasswordServices passwordServices;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponseDTO createUser(UserDTO userDTO) throws Exception {
        if (userDTO.rolId() == null) {
            throw new RolNotExistsException(userDTO.rolId());
        }
        RolResponseDTO rol = rolServices.getRol(userDTO.rolId());

        if (userRepository.findByCc(userDTO.cc()).isPresent()){
            throw new CcUserExistsException(userDTO.cc());
        }
        if (userRepository.findByEmail(userDTO.email().toLowerCase()).isPresent()){
            throw new EmailUserExistsException(userDTO.email());
        }
        if (userRepository.findByPhone(userDTO.phone()).isPresent()) {
            throw new PhoneUserExistsException(userDTO.phone());
        }

        if(rol == null){
            throw new RolNotExistsException(rol.id());
        }

        User user = userRepository.save(new User(userDTO, new Rol(rol), userDTO.email().toLowerCase(),
                passwordServices.encryptPasswords(userDTO.password())));
        return new UserResponseDTO(user.getId(), user.getCc(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(), new RolResponseDTO(rol.id(),
                rol.rolName()));

    }

    @Override
    public List<UserResponseDTO> listUsers() throws Exception {
        List<User> userlist = userRepository.findAll();

        if (userlist.isEmpty()){
            throw new UsersNotExistsException();
        }

        return userlist.stream().map(
                r -> new UserResponseDTO(r.getId(), r.getCc(), r.getName(), r.getLastname(), r.getPhone(),
                        r.getEmail(), r.getPassword(), new RolResponseDTO(r.getRol().getId(), r.getRol().getRolName()))).toList();
    }

    @Override
    public UserResponseDTO getUser(Long cc) throws Exception {
        User user = getUserEntity(cc);

        return new UserResponseDTO(user.getId(), user.getCc(), user.getName(), user.getLastname(), user.getPhone(),
                user.getEmail(), user.getPassword(), new RolResponseDTO(user.getRol().getId(), user.getRol().getRolName()));
    }

    @Override
    public User getUserEntity(Long cc) throws Exception {
        return userRepository.findByCc(cc).orElseThrow(() -> new UserNotExistException(cc));
    }

    @Override
    public UserResponseDTO deleteUser(Long cc) throws Exception {
        try{
            UserResponseDTO user = getUser(cc);
            userRepository.deleteByCc(cc);
            return user;
        }catch (EmptyResultDataAccessException e){
            throw new UserNotExistException(cc);
        }
    }

    
}
