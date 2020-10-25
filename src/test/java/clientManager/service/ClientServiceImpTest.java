package clientManager.service;

import clientManager.dto.ClientMortalityData;
import clientManager.dto.ClientsAgeData;
import clientManager.model.Client;
import clientManager.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
/**
 * @author Agustin Perez Garcia
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImpTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImp clientServiceImp;

    private List<Client> clientList;

    @Before
    public void preloadDate() throws ParseException {
        Date birthDateClient1 = new SimpleDateFormat("dd/MM/yyyy").parse("06/05/1989");
        Date birthDateClient2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/10/1985");
        Client clientTest1 = new Client("Roberto", "Gutierrez", 30, birthDateClient1);
        Client clientTest2 = new Client("Juan", "Gomez", 36, birthDateClient2);
        clientList = new ArrayList();
        clientList.add(clientTest1);
        clientList.add(clientTest2);
    }

    @Test
    public void validCalculationClientAgeData() {
        when(clientRepository.findAll()).thenReturn(clientList);
        ClientsAgeData resultClientAgeData = clientServiceImp.getClientsAgeData();
        Assert.assertEquals(33, resultClientAgeData.getAverageAge(), 0);
        Assert.assertEquals(3, resultClientAgeData.getStandardDeviation(), 0);
    }

    @Test
    public void errorWhenCalculateClientAgeDataWithEmptyClientList() {
        when(clientRepository.findAll()).thenReturn(new ArrayList());
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            clientServiceImp.getClientsAgeData();
        });
    }

    @Test
    public void validCalculationClientMortalityData() throws ParseException {
        when(clientRepository.findAll()).thenReturn(clientList);
        List<ClientMortalityData> resultClientMortalityData = clientServiceImp.getClientsMortalityData();

        Date expectedMortalityClient1 = new SimpleDateFormat("dd/MM/yyyy").parse("06/05/2061");
        Assert.assertEquals(expectedMortalityClient1, resultClientMortalityData.get(0).getMortalityDate());
        Assert.assertEquals("Roberto", resultClientMortalityData.get(0).getClient().getName());
        Assert.assertEquals("Gutierrez", resultClientMortalityData.get(0).getClient().getSurname());
        Assert.assertEquals(30, resultClientMortalityData.get(0).getClient().getAge(),0);
        Date expectedBirthDateClient1 = new SimpleDateFormat("dd/MM/yyyy").parse("06/05/1989");
        Assert.assertEquals(expectedBirthDateClient1, resultClientMortalityData.get(0).getClient().getBirthDate());

        Date expectedMortalityClient2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2057");
        Assert.assertEquals(expectedMortalityClient2, resultClientMortalityData.get(1).getMortalityDate());
        Assert.assertEquals("Juan", resultClientMortalityData.get(1).getClient().getName());
        Assert.assertEquals("Gomez", resultClientMortalityData.get(1).getClient().getSurname());
        Assert.assertEquals(36, resultClientMortalityData.get(1).getClient().getAge(),0);
        Date expectedBirthDateClient2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/10/1985");
        Assert.assertEquals(expectedBirthDateClient2, resultClientMortalityData.get(1).getClient().getBirthDate());

    }

    @Test
    public void errorWhenCalculateClientMortalityDataWithEmptyClientList() {
        when(clientRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            clientServiceImp.getClientsMortalityData();
        });
    }
}