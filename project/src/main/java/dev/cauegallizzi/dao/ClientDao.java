package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Client;

public class ClientDao extends GenericDao<Client, Long> implements IClientDao {

    public ClientDao() {
        super();
    }

    @Override
    public Class<Client> getClassType() {
        return Client.class;
    }

    @Override
    public void updateData(Client entity, Client entityRegistered) {
        entityRegistered.setCity(entity.getCity());
        entityRegistered.setCpf(entity.getCpf());
        entityRegistered.setEnd(entity.getEnd());
        entityRegistered.setState(entity.getState());
        entityRegistered.setName(entity.getName());
        entityRegistered.setNumber(entity.getNumber());
        entityRegistered.setPhone(entity.getPhone());
    }
}
