package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.clientDTOS.AdressDTO;
import com.api.facturacion.domain.dtos.clientDTOS.ClientDTO;
import com.api.facturacion.domain.dtos.clientDTOS.ClientResponseDTO;
import com.api.facturacion.domain.models.Adress;
import com.api.facturacion.domain.models.Client;
import com.api.facturacion.domain.repository.ClientRepository;
import com.api.facturacion.infrastructure.exceptions.clientExceptions.*;
import com.api.facturacion.infrastructure.exceptions.rolExceptions.RolNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServicesImpl implements ClientServices{
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public ClientResponseDTO createClient(ClientDTO clientDTO) throws Exception {

        if(clientRepository.findByCc(clientDTO.cc()).isPresent()){
            throw new CcClientExistsException(clientDTO.cc());
        }

        if (clientRepository.findByPhone(clientDTO.phone()).isPresent()){
            throw new PhoneClientExistsException(clientDTO.phone());
        }

        if (clientRepository.findByEmail(clientDTO.email()).isPresent()){
            throw new EmailClientExistsException(clientDTO.email());
        }

        Adress adress = new Adress(clientDTO.adress());
        Client client = clientRepository.save(new Client(clientDTO, clientDTO.email().toLowerCase(), adress));

        return new ClientResponseDTO(client.getId(), client.getCc(), client.getName(),client.getLastName(),
                client.getEmail(), client.getPhone(), new AdressDTO(client.getAdress().getCity(),
                client.getAdress().getNeighborhood(), client.getAdress().getStreet(),
                client.getAdress().getComplement(), client.getAdress().getDetails()));
    }

    @Override
    public List<ClientResponseDTO> listClients() throws Exception {
        List<Client> clientList = clientRepository.findAll();

        if (clientList.isEmpty()){
            throw new ClientsNotExistsException();
        }

        return clientList.stream().map(
                c -> new ClientResponseDTO(c.getId(), c.getCc(), c.getName(),c.getLastName(),
                        c.getEmail(), c.getPhone(), new AdressDTO(c.getAdress().getCity(),
                        c.getAdress().getNeighborhood(), c.getAdress().getStreet(),
                        c.getAdress().getComplement(), c.getAdress().getDetails()))
        ).toList();
    }

    @Override
    public ClientResponseDTO getClient(Long cc) throws Exception {
        Client client = clientRepository.findByCc(cc).orElseThrow(() -> new ClientNotExistsException(cc));

        return new ClientResponseDTO(client.getId(), client.getCc(), client.getName(),client.getLastName(),
                client.getEmail(), client.getPhone(), new AdressDTO(client.getAdress().getCity(),
                        client.getAdress().getNeighborhood(), client.getAdress().getStreet(),
                        client.getAdress().getComplement(), client.getAdress().getDetails()));
    }


    @Override
    public ClientResponseDTO deleteClient(Long cc) throws Exception {
        try{
            ClientResponseDTO client = getClient(cc);
            clientRepository.deleteByCc(cc);
            return  client;
        }catch (EmptyResultDataAccessException e){
            throw new ClientNotExistsException(cc);
        }
    }
}
