package dev.cauegallizzi.service;

import dev.cauegallizzi.domain.Client;
import dev.cauegallizzi.mock.ClientDaoMock;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class ClientServiceTest {

    private ClientService service;

    @Before
    public void setUp() {
        service = new ClientService(new ClientDaoMock());
    }

    private Client buildClient(Long cpf, String name) {
        Client client = new Client();
        client.setCpf(cpf);
        client.setName(name);
        client.setPhone(11999999999L);
        client.setEnd("Rua das Flores");
        client.setNumber(100);
        client.setCity("São Paulo");
        client.setState("SP");
        return client;
    }

    @Test
    public void saveShouldReturnTrueWhenClientIsNew() {
        Client client = buildClient(12345678900L, "João");
        assertTrue(service.save(client));
    }

    @Test
    public void saveShouldReturnFalseWhenClientAlreadyExists() {
        Client client = buildClient(12345678900L, "João");
        service.save(client);
        assertFalse(service.save(client));
    }

    @Test
    public void getShouldReturnClientAfterSave() {
        Client client = buildClient(12345678900L, "João");
        service.save(client);

        Client found = service.get(12345678900L);

        assertNotNull(found);
        assertEquals("João", found.getName());
        assertEquals(Long.valueOf(12345678900L), found.getCpf());
    }

    @Test
    public void getShouldReturnNullWhenClientDoesNotExist() {
        assertNull(service.get(99999999999L));
    }

    @Test
    public void updateShouldChangeClientData() {
        service.save(buildClient(12345678900L, "João"));

        Client updated = buildClient(12345678900L, "João Atualizado");
        updated.setCity("Curitiba");
        service.update(updated);

        Client found = service.get(12345678900L);
        assertEquals("João Atualizado", found.getName());
        assertEquals("Curitiba", found.getCity());
    }

    @Test
    public void updateShouldDoNothingWhenClientDoesNotExist() {
        service.update(buildClient(12345678900L, "João"));
        assertNull(service.get(12345678900L));
    }

    @Test
    public void deleteShouldRemoveClient() {
        service.save(buildClient(12345678900L, "João"));
        service.delete(12345678900L);
        assertNull(service.get(12345678900L));
    }

    @Test
    public void deleteShouldDoNothingWhenClientDoesNotExist() {
        service.delete(99999999999L);
        assertNull(service.get(99999999999L));
    }

    @Test
    public void getAllShouldReturnAllSavedClients() {
        service.save(buildClient(11111111111L, "Ana"));
        service.save(buildClient(22222222222L, "Bruno"));
        service.save(buildClient(33333333333L, "Carlos"));

        Collection<Client> clients = service.getAll();
        assertEquals(3, clients.size());
    }

    @Test
    public void getAllShouldReturnEmptyWhenNoClientsExist() {
        assertTrue(service.getAll().isEmpty());
    }
}
