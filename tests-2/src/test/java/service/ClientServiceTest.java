package service;

import dev.cauegallizzi.dao.IClientDao;
import dev.cauegallizzi.domain.Client;
import dev.cauegallizzi.mock.ClientDaoMock;
import dev.cauegallizzi.service.ClientService;
import org.junit.Assert;
import org.junit.Test;

public class ClientServiceTest {

    private IClientDao clientDao;
    private ClientService clientService;

    public ClientServiceTest() {
        this.clientDao = new ClientDaoMock();
        this.clientService = new ClientService(clientDao);
    }

    @Test
    public void shouldSave() {
        String result = clientService.save();
        Assert.assertEquals("Success", result);
    }

    @Test
    public void shouldReturnClientById() {
        Client result = clientService.getById(1);
        Client expected = new Client(1, "John Doe", "Rua Teste 123");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldUpdateClientById() {
        Client result = clientService.update(1, "Marie", "Rua Teste 321");
        Client expected = new Client(1, "Marie", "Rua Teste 321");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldDeleteClientById() {
        String result = clientService.delete(1);
        Assert.assertEquals("Success", result);
    }
}
