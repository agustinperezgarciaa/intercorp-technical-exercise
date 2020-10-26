package clientManager.service;

import java.util.*;

import clientManager.dto.ClientMortalityData;
import clientManager.dto.ClientsAgeData;
import clientManager.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import clientManager.repository.ClientRepository;

/**
 * @author Agustin Perez Garcia
 *
 */
@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    public List<ClientMortalityData> getClientsMortalityData() {
        List<Client> clients = getAllClients();
        checkEmptyClients(clients, "mortality data");
        List<ClientMortalityData> clientMortalityDataList = new ArrayList();
        for(Client client: clients){
            Date mortalityData = calculateMortalityDate(client);
            ClientMortalityData clientMortalityData = new ClientMortalityData(client, mortalityData);
            clientMortalityDataList.add(clientMortalityData);
        }
        return clientMortalityDataList;
    }

    public ClientsAgeData getClientsAgeData() {
        List<Client> clients = getAllClients();
        checkEmptyClients(clients, "age data");
        double averageAge = calculateAverageAge(clients);
        double standardDeviation = calculateStandardDeviation(averageAge, clients);
        return new ClientsAgeData(averageAge, standardDeviation);
    }

    private void checkEmptyClients(List<Client> clients, String dataType) throws EmptyResultDataAccessException {
        if (clients.isEmpty()){
            throw new EmptyResultDataAccessException("Cannot calculate "+ dataType +" without clients", 1);
        }
    }

    private double calculateStandardDeviation(double averageAge, List<Client> clients) {
        double variance = 0.0;
        for(Client client: clients){
            double range;
            range = Math.pow(client.getAge() - averageAge, 2f);
            variance = variance + range;
        }
        variance = variance / clients.size();
        double deviation = Math.sqrt(variance);
        return deviation;
    }

    private long calculateAverageAge(List<Client> clients) {
        Integer ageSumatory = 0;
        for(Client client: clients){
            ageSumatory += client.getAge();
        }
        return ageSumatory/clients.size();
    }

    private Date calculateMortalityDate(Client client) {
        final Integer WORLD_AVERAGE_MORTALITY_AGE = 72;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(client.getBirthDate());
        calendar.add(Calendar.YEAR, WORLD_AVERAGE_MORTALITY_AGE);
        return calendar.getTime();
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

}