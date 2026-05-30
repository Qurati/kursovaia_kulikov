package ru.qurati.metalsapp.repository;

import ru.qurati.metalsapp.model.Client;

public class ClientDao extends BaseDao<Client> {
    public ClientDao() {
        super(Client.class);
    }
}