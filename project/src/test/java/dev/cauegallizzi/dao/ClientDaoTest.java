package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Client;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class ClientDaoTest {

    private ClientDao dao;

    @Before
    public void setUp() {
        SingletonMap.getInstance().getMap().clear();
        dao = new ClientDao();
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
        assertTrue(dao.save(client));
    }

    @Test
    public void saveShouldReturnFalseWhenClientAlreadyExists() {
        Client client = buildClient(12345678900L, "João");
        dao.save(client);
        assertFalse(dao.save(client));
    }

    @Test
    public void getShouldReturnClientAfterSave() {
        Client client = buildClient(12345678900L, "João");
        dao.save(client);

        Client found = dao.get(12345678900L);

        assertNotNull(found);
        assertEquals("João", found.getName());
        assertEquals(Long.valueOf(12345678900L), found.getCpf());
    }

    @Test
    public void getShouldReturnNullWhenClientDoesNotExist() {
        assertNull(dao.get(99999999999L));
    }

    @Test
    public void updateShouldChangeClientData() {
        Client client = buildClient(12345678900L, "João");
        dao.save(client);

        Client updated = buildClient(12345678900L, "João Atualizado");
        updated.setCity("Rio de Janeiro");
        updated.setState("RJ");
        dao.update(updated);

        Client found = dao.get(12345678900L);
        assertEquals("João Atualizado", found.getName());
        assertEquals("Rio de Janeiro", found.getCity());
        assertEquals("RJ", found.getState());
    }

    @Test
    public void updateShouldDoNothingWhenClientDoesNotExist() {
        Client client = buildClient(12345678900L, "João");
        dao.update(client);

        assertNull(dao.get(12345678900L));
    }

    @Test
    public void deleteShouldRemoveClient() {
        Client client = buildClient(12345678900L, "João");
        dao.save(client);

        dao.delete(12345678900L);

        assertNull(dao.get(12345678900L));
    }

    @Test
    public void deleteShouldDoNothingWhenClientDoesNotExist() {
        dao.delete(99999999999L);
        assertNull(dao.get(99999999999L));
    }

    @Test
    public void getAllShouldReturnAllSavedClients() {
        dao.save(buildClient(11111111111L, "Ana"));
        dao.save(buildClient(22222222222L, "Bruno"));
        dao.save(buildClient(33333333333L, "Carlos"));

        Collection<Client> clients = dao.getAll();

        assertEquals(3, clients.size());
    }

    @Test
    public void getAllShouldReturnEmptyWhenNoClientsExist() {
        assertTrue(dao.getAll().isEmpty());
    }
}
