package dev.cauegallizzi.service;

import dev.cauegallizzi.dao.IClientDao;
import dev.cauegallizzi.domain.Client;

public class ClientService {

    private IClientDao clientDao;

    public ClientService(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public String save() {
        try {
            return clientDao.save();
        } catch (Exception error) {
            throw new RuntimeException();
        }
    }

    public Client getById(Integer id) {
        try {
            return clientDao.getById(id);
        } catch (Exception error) {
            throw new RuntimeException();
        }
    }

    public Client update(Integer id, String name, String address) {
        try {
            return clientDao.update(id, name, address);
        } catch (Exception error) {
            throw new RuntimeException();
        }
    }

    public String delete(Integer id) {
        try {
            return clientDao.delete(id);
        } catch (Exception error) {
            throw new RuntimeException();
        }
    }
}
