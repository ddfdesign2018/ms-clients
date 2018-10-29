package com.ddfdesign.msclients.service;

import com.ddfdesign.msclients.entity.Client;
import com.ddfdesign.msclients.repository.IClientsRepository;
import com.ddfdesign.msclients.rest.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestionClients implements IGestionClients{

    @Autowired
    IClientsRepository clientsRepository;

    /**
     *
     * @return
     */
    @Override
    public List<ClientDTO> getAllClients(){
        List<ClientDTO> lista = clientsRepository.findAll()
                .stream()
                .map(
                        clientEntity -> new ClientDTO(
                                clientEntity.getId(),
                                clientEntity.getName(),
                                clientEntity.getSurnames(),
                                clientEntity.getDni(),
                                clientEntity.getPhone(),
                                clientEntity.getAddress(),
                                clientEntity.getAddress(),
                                clientEntity.getStatus(),
                                clientEntity.getLast_buy()
                        )
                ).collect(Collectors.toList());
        return lista;
    }

    /**
     *
     * @param idClient
     * @return
     */
    @Override
    public ClientDTO getClientById(Long idClient){
        Optional<Client> clientOptional = clientsRepository.findById(idClient);
        if (!clientOptional.isPresent())
            return null;
        Client client = clientOptional.get();
        ClientDTO clientDTO = new ClientDTO(
                client.getId(),
                client.getName(),
                client.getSurnames(),
                client.getDni(),
                client.getPhone(),
                client.getAddress(),
                client.getEmail(),
                client.getStatus(),
                client.getLast_buy()
        );
        return clientDTO;
    }

    /**
     *
     * @param clientDTO
     * @return
     */
    @Override
    public ClientDTO createClientById(ClientDTO clientDTO){
        Client client = new Client(
                null,
                clientDTO.getName(),
                clientDTO.getSurnames(),
                clientDTO.getDni(),
                clientDTO.getPhone(),
                clientDTO.getAddress(),
                clientDTO.getEmail(),
                clientDTO.getStatus(),
                clientDTO.getLast_buy()
        );
        Client resultado = clientsRepository.save(client);
        return new ClientDTO(resultado.getId(),
                resultado.getName(),
                resultado.getSurnames(),
                resultado.getDni(),
                resultado.getPhone(),
                resultado.getAddress(),
                resultado.getEmail(),
                resultado.getStatus(),
                resultado.getLast_buy()
                );
    }


    /**
     *
     * @param idClient
     * @return
     */
    @Override
    public boolean deleteClientById(Long idClient){
        boolean resultado = false;
        clientsRepository.deleteById(idClient);
        Optional<Client> clientOptional = clientsRepository.findById(idClient);
        if (!clientOptional.isPresent())
            resultado = true;
        return resultado;
    }

    /**
     *
     * @param clientDTO
     * @return
     */
    @Override
    public boolean updateClientById(ClientDTO clientDTO){
        Client client = new Client(
                clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getSurnames(),
                clientDTO.getDni(),
                clientDTO.getPhone(),
                clientDTO.getAddress(),
                clientDTO.getEmail(),
                clientDTO.getStatus(),
                clientDTO.getLast_buy()
        );
        clientsRepository.save(client);
        return true;
    }

    @Override
    public List<ClientDTO> getClientByDni(String dni) {
        List<ClientDTO> clientsDni = new ArrayList<>();
        List<Client> clientList = clientsRepository.findByDni(dni);
        clientList.forEach(client -> {
            ClientDTO clientDTO = new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getSurnames(),
                    client.getDni(),
                    client.getPhone(),
                    client.getAddress(),
                    client.getEmail(),
                    client.getStatus(),
                    client.getLast_buy());
            clientsDni.add(clientDTO);
            return;
        });

        return clientsDni;
    }
}
