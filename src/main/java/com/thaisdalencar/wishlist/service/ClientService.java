package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.exception.NotFoundException;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found clientId: %d", id)));
    }

    public Optional<Long> deleteById(long id) {
        return clientRepository.deleteById(id);
    }

    public Client updateById(Client client, long id) {
        var savedClient = findById(id);
        savedClient.setName(client.getName());
//        savedClient.setEmail(client.getEmail());
        return clientRepository.save(client);
    }
}
