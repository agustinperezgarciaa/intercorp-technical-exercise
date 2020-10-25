package clientManager.dto;

import clientManager.model.Client;

import java.util.Date;
/**
 * @author Agustin Perez Garcia
 *
 */
public class ClientMortalityData {

    private Client client;

    private Date mortalityDate;

    public ClientMortalityData(Client client, Date mortalityDate) {
        this.client = client;
        this.mortalityDate = mortalityDate;
    }

    public Client getClient(){
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getMortalityDate(){
        return mortalityDate;
    }

    public void setMortalityDate(Date mortalityDate){
        this.mortalityDate = mortalityDate;
    }
}
