package dev.cauegallizzi.mock;

import dev.cauegallizzi.dao.IClientDao;
import dev.cauegallizzi.domain.Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClientDaoMock implements IClientDao {

    private final Map<Long, Client> storage = new HashMap<>();

    @Override
    public Boolean save(Client entity) {
        if (storage.containsKey(entity.getCpf())) {
            return false;
        }
        storage.put(entity.getCpf(), entity);
        return true;
    }

    @Override
    public void update(Client entity) {
        Client registered = storage.get(entity.getCpf());
        if (registered != null) {
            registered.setName(entity.getName());
            registered.setPhone(entity.getPhone());
            registered.setEnd(entity.getEnd());
            registered.setNumber(entity.getNumber());
            registered.setCity(entity.getCity());
            registered.setState(entity.getState());
            registered.setCpf(entity.getCpf());
        }
    }

    @Override
    public void delete(Long key) {
        storage.remove(key);
    }

    @Override
    public Client get(Long key) {
        return storage.get(key);
    }

    @Override
    public Collection<Client> getAll() {
        return storage.values();
    }
}
