package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.exception.NotFoundException;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        try {
            return clientRepository.save(client);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found clientId: %d", id)));
    }

    public Long deleteById(long id) {
        return clientRepository.deleteById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found clientId: %d", id)));
    }

    public Client updateById(Client client, long id) {
        var savedClient = findById(id);
        savedClient.setName(client.getName());
        savedClient.setEmail(client.getEmail()); //todo: ajeitar o problema da constraint
        return clientRepository.save(client);
    }
}
