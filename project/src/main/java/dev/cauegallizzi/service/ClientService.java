package dev.cauegallizzi.service;

import dev.cauegallizzi.dao.IClientDao;
import dev.cauegallizzi.domain.Client;

public class ClientService extends GenericService<Client, Long> implements IClientService {
    public ClientService(IClientDao dao) {
        super(dao);
    }
}
