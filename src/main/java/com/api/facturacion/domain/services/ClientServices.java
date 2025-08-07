package com.api.facturacion.domain.services;

import com.api.facturacion.domain.dtos.clientDTOS.ClientDTO;
import com.api.facturacion.domain.dtos.clientDTOS.ClientResponseDTO;
import com.api.facturacion.domain.models.Client;

import java.util.List;

public interface ClientServices {
    ClientResponseDTO createClient(ClientDTO clientDTO) throws Exception;

    List<ClientResponseDTO> listClients() throws Exception;

    ClientResponseDTO getClient(Long cc) throws Exception;

    Client getClientEntity(Long cc) throws Exception;

    ClientResponseDTO deleteClient(Long id) throws Exception;
}
