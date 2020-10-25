package clientManager.controller;

import java.util.List;

import clientManager.dto.ClientMortalityData;
import clientManager.dto.ClientsAgeData;
import clientManager.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import clientManager.service.ClientService;

/**
 * @author Agustin Perez Garcia
 *
 */
@RestController
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/creacliente")
    public Client saveClient(Client client) {
        clientService.saveClient(client);
        return client;
    }

    @GetMapping("/kpideclientes")
    public ClientsAgeData getKpideclientes() {
        ClientsAgeData clientsAgeDate = clientService.getClientsAgeData();
        return clientsAgeDate;
    }

    @GetMapping("/listclientes")
    public List<ClientMortalityData> getClients() {
        List<ClientMortalityData> clientsMortalityDataList = clientService.getClientsMortalityData();
        return clientsMortalityDataList;
    }
}