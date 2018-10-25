package com.ddfdesign.msclients.service;

import com.ddfdesign.msclients.rest.dto.ClientDTO;

import java.util.List;

public interface IGestionClients {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long idClient);
    ClientDTO createClientById(ClientDTO clientDTO);
    boolean deleteClientById(Long idClient);
    boolean updateClientById(ClientDTO clientDTO);
}
