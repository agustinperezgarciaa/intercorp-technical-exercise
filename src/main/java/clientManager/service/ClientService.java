package clientManager.service;

import clientManager.dto.ClientMortalityData;
import clientManager.dto.ClientsAgeData;
import clientManager.model.Client;

import java.util.List;

/**
 * @author Agustin Perez Garcia
 *
 */
public interface ClientService {

    public void saveClient(Client client);

    public ClientsAgeData getClientsAgeData();

    public List<ClientMortalityData> getClientsMortalityData();

}