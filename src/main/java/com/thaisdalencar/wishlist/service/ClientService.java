package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(long id) {
        return clientRepository.findById(id);
    }

    public Optional<Long> deleteById(long id) {
        return clientRepository.deleteById(id);
    }
}
