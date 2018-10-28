package com.ddfdesign.msclients.rest;

import com.ddfdesign.msclients.rest.dto.ClientDTO;
import com.ddfdesign.msclients.service.IGestionClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class ClientResource {

    @Autowired
    IGestionClients gestionClients;

    /**
     *
     * @return
     */
    @RequestMapping(value = "client", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> listaClients = gestionClients.getAllClients();
        return ResponseEntity.ok(listaClients);
    }

    /**
     *
     * @param idClient
     * @return
     */
    @RequestMapping(value = "client/{idClient}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> getClientByIdClient(@PathVariable Long idClient) {
        ClientDTO listaClient = gestionClients.getClientById(idClient);
        if (listaClient == null)
            return null;
        return ResponseEntity.ok(listaClient);
    }

    /**
     *
     * @param clientDTO
     * @return
     */
    @RequestMapping(value = "client", method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO resultado = gestionClients.createClientById(clientDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     *
     * @param idClient
     * @return
     */
    @RequestMapping(value = "client/{idClient}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClient(@PathVariable Long idClient) {
        String resultado = "";
        if (gestionClients.deleteClientById(idClient))
            resultado = "El client con id " + idClient + " Ha sido eliminado correctamente";
        else
            resultado = "El client con id " + idClient + " No se ha podido borrar";
        return ResponseEntity.ok(resultado);
    }

    /**
     *
     * @param clientDTO
     * @return
     */
    @RequestMapping(value = "client", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateClient(@RequestBody ClientDTO clientDTO) {
        boolean resultado = gestionClients.updateClientById(clientDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "clientList", method = RequestMethod.GET)
    public List<ClientDTO> getAllClientsList() {
        List<ClientDTO> listaClients = gestionClients.getAllClients();
        return listaClients;
    }

    @RequestMapping(value = "clientOne/{idClient}", method = RequestMethod.GET)
    public ClientDTO getClientByIdClientOne(@PathVariable Long idClient) {
        ClientDTO listaClient = gestionClients.getClientById(idClient);
        if (listaClient == null)
            return null;
        return listaClient;
    }
}
