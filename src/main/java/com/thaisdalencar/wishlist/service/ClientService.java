package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.controller.request.PaginationRequest;
import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.exception.NotFoundException;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

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
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public Page<Client> findAll(PaginationRequest page) {
        return clientRepository.findAll(page.getPagination());
    }

    public Client findById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found clientId: %d", id)));
    }

    public void deleteById(long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format("Not found clientId: %d", id));
        }
    }

    public Client updateById(Client client, long id) {
        var savedClient = findById(id);
        savedClient.setName(client.getName());
        savedClient.setEmail(client.getEmail()); //todo: ajeitar o problema da constraint
        return clientRepository.save(client);
    }
}
