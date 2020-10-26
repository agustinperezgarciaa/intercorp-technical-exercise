package clientManager.controller;

import java.util.List;

import clientManager.dto.ClientMortalityData;
import clientManager.dto.ClientsAgeData;
import clientManager.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import clientManager.service.ClientService;

import javax.validation.Valid;

/**
 * @author Agustin Perez Garcia
 *
 */
@RestController
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/creacliente")
    public ResponseEntity createNewClient(@Valid @RequestBody Client client) {
        clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/kpideclientes")
    public ResponseEntity getClientsAgeData() {
        ClientsAgeData clientsAgeDate = clientService.getClientsAgeData();
        return ResponseEntity.ok(clientsAgeDate);
    }

    @GetMapping("/listclientes")
    public ResponseEntity getClientsMortalityDate() {
        List<ClientMortalityData> clientsMortalityDataList = clientService.getClientsMortalityData();
        return ResponseEntity.ok(clientsMortalityDataList);
    }
}